package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.generated.model.JobBulkStatusResponseImpl
import org.veupathdb.service.mblast.query.generated.resources.Statuses
import org.veupathdb.service.mblast.query.mixins.toIOType
import java.util.stream.Stream

class JobStatusController(@Context request: ContainerRequest) : ControllerBase(request), Statuses {

  override fun postStatuses(entity: MutableList<String?>?): Statuses.PostStatusesResponse {
    if (entity == null)
      throw BadRequestException()

    val res = JobBulkStatusResponseImpl()

    // With the list of job IDs
    entity.stream()
      // Filter out any entries in the input that were null
      .filterToNN()
      // Attempt to convert the entries in the input into HashID instances
      // (or null if the value is not a valid HashID)
      .map { try { HashID(it as String) } catch (e: Throwable) { null } }
      // Filter out any nulls
      .filterToNN()
      // Parallelize as we are about to do so IO
      .parallel()
      // Fetch the job details from S3 (or null if the job doesn't exist)
      .map { AsyncPlatform.getJob(it) }
      // Filter out any nulls
      .filterToNN()
      // Trim down and transform
      .map { Pair(it.jobID, it.status.toIOType()) }
      // Collect to list as an endcap, ending the parallelization of the stream
      .toList()
      // Synchronously iterate through the results and append to our output
      // object.
      .forEach { (id, status) -> res.setAdditionalProperties(id.string, status) }

    return Statuses.PostStatusesResponse.respond200WithApplicationJson(res)
  }

  @Suppress("UNCHECKED_CAST")
  private inline fun <T> Stream<T?>.filterToNN(): Stream<T> =
    this.filter { it != null } as Stream<T>
}