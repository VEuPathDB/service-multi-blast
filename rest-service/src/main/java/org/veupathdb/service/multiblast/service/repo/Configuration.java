package org.veupathdb.service.multiblast.service.repo;

import java.util.function.Supplier;

import io.vulpine.lib.sql.load.SqlLoader;

class Schema
{
  static final String Blast = "blast";
  static final String Jobs  = "jobs";
}

class Table
{
  static class Blast
  {
    static final String
      Options        = "options",
      Tasks          = "tasks",
      Tools          = "tools",
      ToolsToOptions = "tools_to_options";
  }

  static class Jobs
  {
    static final String
      Jobs   = "jobs",
      Status = "status";
  }
}

class Column
{
  static class Blast
  {
    static class Tasks
    {
      static final String
        TaskID = "task_id",
        ToolID = "tool_id",
        Name   = "name";
    }
  }
}

class SQL
{
  private static final SqlLoader loader = new SqlLoader();

  static class Delete
  {

  }

  static class Insert
  {
    static class Job
    {
    }
  }

  static class Select
  {
    static class Blast
    {
      static final String
        Options        = select(join(Schema.Blast, Table.Blast.Options, "all")),
        Tasks          = select(join(Schema.Blast, Table.Blast.Tasks, "all")),
        Tools          = select(join(Schema.Blast, Table.Blast.Tools, "all")),
        ToolsToOptions = select(join(Schema.Blast, Table.Blast.ToolsToOptions, "all"));
    }

    static class Job
    {
    }
  }

  static class Update
  {
    static class Job
    {
    }
  }

  private static String join(String... parts) {
    return String.join(".", parts);
  }

  private static String delete(String path) {
    return loader.delete(path).orElseThrow(makeError("delete", path));
  }

  private static String insert(String path) {
    return loader.insert(path).orElseThrow(makeError("insert", path));
  }

  private static String select(String path) {
    return loader.select(path).orElseThrow(makeError("select", path));
  }

  private static String update(String path) {
    return loader.udpate(path).orElseThrow(makeError("update", path));
  }

  private static Supplier< RuntimeException > makeError(String mode, String path) {
    return () -> new RuntimeException(String.format(
      "Failed to load query main/resources/sql/%s/%s.sql",
      mode,
      path.replace('.', '/')
    ));
  }
}
