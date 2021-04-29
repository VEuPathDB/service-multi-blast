package mb.api.service.http.job;

import java.io.File;

import mb.api.model.internal.Job;
import mb.api.service.cli.CliBuilder_;
import mb.lib.db.model.JobTarget;
import mb.lib.model.HashID;

public class JobDetails
{
  File        query;
  HashID      id;
  Job         job;
  CliBuilder_ cli;
  long        userID;
  String      description;
  Long        maxDlSize;
  HashID      parentHash;
  boolean     isPrimary;
  String      projectID;
  JobTarget[] targets;
}
