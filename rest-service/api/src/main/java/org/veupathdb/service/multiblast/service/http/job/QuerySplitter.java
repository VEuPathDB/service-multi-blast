package org.veupathdb.service.multiblast.service.http.job;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.veupathdb.service.multiblast.util.Format;

class QuerySplitter // TODO: PARENT JOB SHOULD BE ON THE USER ROW
{
  private final List<BufferedWriter> writing;
  private final List<MessageDigest>  hashing;
  private final List<File>           active;
  private final QuerySplitResult     output;

  private File rootFile;
  private int  queries;

  QuerySplitter() {
    queries = 1;
    writing = new ArrayList<>(2);
    hashing = new ArrayList<>(2);
    active  = new ArrayList<>(2);
    output  = new QuerySplitResult();
  }

  QuerySplitResult splitQueries(InputStream stream) throws Exception {
    rootFile = newTmpFile();

    writing.add(new BufferedWriter(new FileWriter(rootFile)));
    hashing.add(MessageDigest.getInstance(Format.HASH_TYPE));
    active.add(rootFile);

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

      output.rootQuery = new QuerySplitRow(rootFile, hashing.get(0).digest());
    }

    return output;
  }

  private void close() throws Exception {
    for (var i = 0; i < active.size(); i++) {
      writing.get(i).flush();
      writing.get(i).close();
    }
  }

  private void writeLine(String line) throws Exception {
    for (var i = 0; i < active.size(); i++) {
      writing.get(i).write(line);
      writing.get(i).newLine();
      hashing.get(i).update(line.getBytes(StandardCharsets.UTF_8));
    }
  }

  private void forkRoot() throws Exception {
    // We've hit a second query, create a copy of the root query to be
    // the first subquery.
    var subQueryPath = newTmpPath();

    Files.copy(rootFile.toPath(), subQueryPath);
    var subQueryFile = subQueryPath.toFile();

    if (!subQueryFile.exists())
      throw new Exception("Failed to copy first subquery");

    output.subQueries.add(new QuerySplitRow(subQueryFile, hashing.get(0).digest()));

    // Setup a new file for the next query in the input.
    var second = newTmpFile();
    active.add(second);
    writing.add(new BufferedWriter(new FileWriter(second)));
    hashing.add(MessageDigest.getInstance(Format.HASH_TYPE));
  }

  private void nextFile() throws Exception {
    // Close the last subquery
    output.subQueries.add(new QuerySplitRow(active.get(1), hashing.get(1).digest()));
    writing.get(1).flush();
    writing.get(1).close();

    // Setup a new subquery file
    var second = newTmpFile();
    writing.set(1, new BufferedWriter(new FileWriter(second)));
    hashing.get(1).reset();
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
}

class QuerySplitRow
{
  final File   source;
  final byte[] hash;

  QuerySplitRow(File source, byte[] hash) {
    this.source = source;
    this.hash   = hash;
  }
}