package mb.api.service.http.job;

import java.io.File;

import mb.lib.db.model.JobTarget;
import mb.lib.model.HashID;
import mb.api.model.internal.Job;
import mb.api.service.cli.CliBuilder;

public class JobDetails
{
  File    query;
  HashID  id;
  Job     job;
  CliBuilder  cli;
  long        userID;
  String      description;
  Long    maxDlSize;
  HashID  parentHash;
  boolean isPrimary;
  String      projectID;
  JobTarget[] targets;
}
