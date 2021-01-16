package org.veupathdb.service.multiblast.service.http;

import java.nio.file.Path;
import java.util.Base64;
import java.util.Collections;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;

import mb.lib.config.Config;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;

class Util
{
  static WebApplicationException wrapException(Exception e) {
    if (e instanceof WebApplicationException)
      return (WebApplicationException) e;

    return new InternalServerErrorException(e);
  }

  static Path createJobDir(int jobId) {
    var path = Path.of(Config.getInstance().getJobMountPath(), String.valueOf(jobId));
    var file = path.toFile();

    if (file.exists())
      throw new InternalServerErrorException("re-issued job id " + jobId);
    if (!file.mkdir())
      throw new InternalServerErrorException("failed to create job data directory");

    return path;
  }

  static byte[] parseUrlJobID(String jobId) {
    if (jobId == null || jobId.isBlank()) {
      throw new NotFoundException();
    }

    try {
      return Base64.getDecoder().decode(jobId);
    } catch (IllegalArgumentException e) {
      throw new NotFoundException();
    }
  }

  static byte[] parseBodyJobID(String key, String jobId) {
    if (jobId == null || jobId.isBlank()) {
      throw new UnprocessableEntityException(
        Collections.singletonMap(key, Collections.singletonList("invalid ID"))
      );
    }

    try {
      return Base64.getDecoder().decode(jobId);
    } catch (IllegalArgumentException e) {
      throw new UnprocessableEntityException(
        Collections.singletonMap(key, Collections.singletonList("invalid ID"))
      );
    }
  }
}
