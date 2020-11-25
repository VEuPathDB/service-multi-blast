package org.veupathdb.service.multiblast.service.http;

import java.nio.file.Path;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;

import org.veupathdb.service.multiblast.Config;

class Util
{
  static WebApplicationException wrapException(Exception e) {
    if (e instanceof WebApplicationException)
      return (WebApplicationException) e;

    return new InternalServerErrorException(e);
  }

  static Path createJobDir(int jobId) {
    var path = Path.of(Config.getInstance().getMountPath(), String.valueOf(jobId));
    var file = path.toFile();

    if (file.exists())
      throw new InternalServerErrorException("re-issued job id " + jobId);
    if (!file.mkdir())
      throw new InternalServerErrorException("failed to create job data directory");

    return path;
  }
}
