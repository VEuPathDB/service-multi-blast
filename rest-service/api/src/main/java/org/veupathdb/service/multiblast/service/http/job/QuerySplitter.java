package org.veupathdb.service.multiblast.service.http.job;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.util.Format;

class QuerySplitter // TODO: PARENT JOB SHOULD BE ON THE USER ROW
{
  private static final Logger log = LogManager.getLogger(QuerySplitter.class);

  private final BufferedWriter[] writing;
  private final MessageDigest[]  hashing;
  private final File[]           active;
  private final QuerySplitResult output;

  private File rootFile;
  private int  queries;

  QuerySplitter() {
    log.trace("::new()");

    writing = new BufferedWriter[2];
    hashing = new MessageDigest[2];
    active  = new File[2];
    output  = new QuerySplitResult();
  }

  QuerySplitResult splitQueries(InputStream stream) throws Exception {
    log.trace("#splitQueries(stream={})", stream);

    rootFile = newTmpFile();

    writing[0] = new BufferedWriter(new FileWriter(rootFile));
    hashing[0] = MessageDigest.getInstance(Format.HASH_TYPE);
    active[0]  = rootFile;

    try (var read = new Scanner(stream)) {
      while (read.hasNext()) {
        var line = read.nextLine();

        if (line.startsWith(">")) {
          queries++;

          if (queries == 2) {
            forkRoot();
          } else if (queries > 2) {
            nextFile();
          }
        }

        writeLine(line);
      }

      close();

      output.rootQuery = new QuerySplitRow(rootFile, hashing[0].digest());
    }

    if (output.subQueries.size() > 0)
      log.debug("Primary query: {}", output.rootQuery);
      log.debug("Split queries into subqueries: {}", output.subQueries);
    return output;
  }

  private void close() throws Exception {
    log.trace("#close()");

    if (active[1] != null)
      output.subQueries.add(new QuerySplitRow(active[1], hashing[1].digest()));

    for (var i = 0; i < active.length; i++) {
      if (writing[i] != null) {
        log.debug("Closing writer for " + active[i]);
        writing[i].flush();
        writing[i].close();
      }
    }
  }

  private void writeLine(String line) throws Exception {
    log.trace("#writeLine(line={})", line);

    for (var i = 0; i < active.length; i++) {
      if (writing[i] != null) {
        log.debug("Writing to " + active[i]);

        writing[i].write(line);
        writing[i].newLine();
        hashing[i].update(line.getBytes(StandardCharsets.UTF_8));
      }
    }
  }

  private void forkRoot() throws Exception {
    log.trace("#forkRoot()");

    // We've hit a second query, create a copy of the root query to be
    // the first subquery.
    var subQueryPath = newTmpPath();
    writing[0].flush();

    Files.copy(rootFile.toPath(), subQueryPath);
    var subQueryFile = subQueryPath.toFile();

    if (!subQueryFile.exists())
      throw new Exception("Failed to copy first subquery");

    output.subQueries.add(new QuerySplitRow(subQueryFile, ((MessageDigest) hashing[0].clone()).digest()));

    // Setup a new file for the next query in the input.
    var second = newTmpFile();
    active[1] = second;
    writing[1] = new BufferedWriter(new FileWriter(second));
    hashing[1] = MessageDigest.getInstance(Format.HASH_TYPE);
    log.warn(Format.toHexString(hashing[1].digest()));
  }

  private void nextFile() throws Exception {
    log.trace("#nextFile()");

    // Close the last subquery
    output.subQueries.add(new QuerySplitRow(active[1], hashing[1].digest()));
    writing[1].flush();
    writing[1].close();

    // Setup a new subquery file
    var second = newTmpFile();
    active[1] = second;
    writing[1] = new BufferedWriter(new FileWriter(second));
    hashing[1].reset();
  }

  private static Path newTmpPath() {
    log.trace("#newTmpPath()");
    return Path.of("/tmp/" + UUID.randomUUID().toString());
  }

  private static File newTmpFile() throws Exception {
    log.trace("#newTmpFile()");

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