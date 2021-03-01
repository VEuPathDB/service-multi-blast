package org.veupathdb.service.multiblast.service.http.job;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import mb.lib.config.Config;
import org.veupathdb.service.multiblast.service.valid.SequenceValidator;
import org.veupathdb.service.multiblast.util.Format;

class QuerySplitter
{
  private static final String
    errInvalidSequence = "Invalid character \"%s\" in %s sequence %d (%s) on line %d, character %d.",
    errLongSequence    = "Sequence %d (%s) is too long. Sequence size for %s sequences is capped at %dbps",
    errQueryCount      = "Too many sequences.  Multi-Blast queries can be at most %d sequences",
    errQuerySize       = "Input query too long.  Multi-Blast query size is capped at %dMiB";

  private final BufferedWriter[]  writing;
  private final MessageDigest[]   hashing;
  private final File[]            active;
  private final QuerySplitResult  output;
  private final SequenceValidator validator;

  private File rootFile;
  private int  queries;
  private int  lines;

  QuerySplitter(SequenceValidator val) {
    writing   = new BufferedWriter[2];
    hashing   = new MessageDigest[2];
    active    = new File[2];
    output    = new QuerySplitResult();
    validator = val;
  }

  QuerySplitResult splitQueries(InputStream stream) throws Exception {
    rootFile = newTmpFile();

    writing[0] = new BufferedWriter(new FileWriter(rootFile));
    hashing[0] = MessageDigest.getInstance(Format.HASH_TYPE);
    active[0]  = rootFile;

    // Header/identifier of the most recently started query.
    var identifier = "";
    var chars = 0;
    var totalChars = 0;

    try (var read = new Scanner(stream)) {
      while (read.hasNext()) {
        var line = read.nextLine();
        lines++;

        totalChars += line.length();

        if (line.startsWith(">")) {
          // Validate last query
          if (!validator.isValidLength(chars))
            output.errors.add(String.format(
              errLongSequence,
              queries,
              identifier,
              validator.kind(),
              validator.maxSeqLength())
            );

          chars = 0;
          queries++;
          identifier = line.substring(1).trim().split(" ", 2)[0];

          if (queries == 2) {
            forkRoot();
          } else if (queries > 2) {
            nextFile();
          }
        } else {
          chars += line.length();
          var val = validator.validate(line);
          if (val != null) {
            output.errors.add(String.format(
              errInvalidSequence,
              val.getCharacter(),
              validator.kind(),
              queries,
              identifier,
              lines,
              val.getLinePosition()
              )
            );
          }
        }

        writeLine(line);
      }

      close();

      output.rootQuery = new QuerySplitRow(rootFile, hashing[0].digest());
    }

    // Validate last query length
    if (!validator.isValidLength(chars))
      output.errors.add(String.format(
        errLongSequence,
        queries,
        identifier,
        validator.kind(),
        validator.maxSeqLength())
      );

    // Validate total query count
    if (!validator.isValidQueryCount(queries))
      output.errors.add(String.format(errQueryCount, Config.getInstance().getMaxQueries()));

    // Validate overall query file size.
    if (totalChars > Config.getInstance().getMaxInputQuerySize())
      output.errors.add(String.format(
        errQuerySize,
        Config.getInstance().getMaxInputQuerySize()/1024/1024
      ));

    return output;
  }

  private void close() throws Exception {
    if (active[1] != null)
      output.subQueries.add(new QuerySplitRow(active[1], hashing[1].digest()));

    for (var i = 0; i < active.length; i++) {
      if (writing[i] != null) {
        writing[i].flush();
        writing[i].close();
      }
    }
  }

  private void writeLine(String line) throws Exception {
    for (var i = 0; i < active.length; i++) {
      if (writing[i] != null) {
        writing[i].write(line);
        writing[i].newLine();
        hashing[i].update(line.getBytes(StandardCharsets.UTF_8));
      }
    }
  }

  private void forkRoot() throws Exception {
    // We've hit a second query, create a copy of the root query to be
    // the first subquery.
    var subQueryPath = newTmpPath();
    writing[0].flush();

    Files.copy(rootFile.toPath(), subQueryPath);
    var subQueryFile = subQueryPath.toFile();

    if (!subQueryFile.exists())
      throw new Exception("Failed to copy first subquery");

    output.subQueries.add(new QuerySplitRow(
      subQueryFile,
      ((MessageDigest) hashing[0].clone()).digest()
    ));

    // Setup a new file for the next query in the input.
    var second = newTmpFile();
    active[1]  = second;
    writing[1] = new BufferedWriter(new FileWriter(second));
    hashing[1] = MessageDigest.getInstance(Format.HASH_TYPE);
  }

  private void nextFile() throws Exception {
    // Close the last subquery
    output.subQueries.add(new QuerySplitRow(active[1], hashing[1].digest()));
    writing[1].flush();
    writing[1].close();

    // Setup a new subquery file
    var second = newTmpFile();
    active[1]  = second;
    writing[1] = new BufferedWriter(new FileWriter(second));
    hashing[1].reset();
  }

  private static Path newTmpPath() {
    return Path.of("/tmp/" + UUID.randomUUID().toString());
  }

  private static File newTmpFile() throws Exception {
    var tmp = newTmpPath().toFile();

    if (!tmp.createNewFile())
      throw new RuntimeException("Failed to create temp file for query.");

    return tmp;
  }
}

class QuerySplitResult
{
  QuerySplitRow       rootQuery;
  List<QuerySplitRow> subQueries = new ArrayList<>(3);
  List<String>        errors     = new ArrayList<>(3);

  @SuppressWarnings("ResultOfMethodCallIgnored")
  void release() {
    rootQuery.source.delete();
    for (var row : subQueries)
      row.source.delete();
  }
}

class QuerySplitRow
{
  /**
   * Query File
   */
  final File source;

  /**
   * Query Hash
   */
  final byte[] hash;

  QuerySplitRow(File source, byte[] hash) {
    this.source = source;
    this.hash   = hash;
  }

  @Override
  public String toString() {
    return "{\"source\":\"" + source + "\",\"hash\":\"" + hashToString() + "\"}";
  }

  private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

  public String hashToString() {
    char[] hexChars = new char[hash.length * 2];
    for (int j = 0; j < hash.length; j++) {
      int v = hash[j] & 0xFF;
      hexChars[j * 2]     = HEX_ARRAY[v >>> 4];
      hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars);
  }
}