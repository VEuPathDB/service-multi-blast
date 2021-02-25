package org.veupathdb.service.multiblast.service.http.job;

import java.io.File;

import mb.lib.db.model.JobTarget;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;
import org.veupathdb.service.multiblast.util.Format;

public class JobDetails
{
  File        query;
  byte[]      hash;
  String      id;
  Job         job;
  CliBuilder  cli;
  long        userID;
  String      description;
  Long        maxDlSize;
  byte[]      parentHash;
  boolean     isPrimary;
  String      projectID;
  JobTarget[] targets;
}
