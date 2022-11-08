package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.generated.model.RPCParamsJobExpire
import org.veupathdb.service.mblast.query.generated.resources.RpcJobs
import org.veupathdb.service.mblast.query.service.MBlastPlatform
import java.lang.IllegalArgumentException

@Authenticated(allowGuests = false)
class RPCJobsController(@Context request: ContainerRequest) : ControllerBase(request), RpcJobs {

  override fun postRpcJobsExpire(entity: RPCParamsJobExpire?): RpcJobs.PostRpcJobsExpireResponse {
    // Ensure we have a request body.
    entity ?: throw BadRequestException("Body was empty.")
    // Ensure we have a target query job ID.
    entity.queryJobID ?: throw BadRequestException("queryJobID was null.")

    // Parse the job ID
    val jobID = try { HashID(entity.queryJobID) } catch (e: IllegalArgumentException) { throw BadRequestException("queryJobID was invalid.") }

    // Get the job (must be attached to the user)
    val job = MBlastPlatform.getJob(jobID, userID) ?: throw ForbiddenException("queryJobID was not found for the requesting user.")

    // If the job doesn't exist in S3, then it counts as expired already.
    job.second ?: return RpcJobs.PostRpcJobsExpireResponse.respond204()

    // If the job hasn't finished yet
    if (!job.second!!.status.isFinished)
      throw ForbiddenException("Attempted to expire a job that has not yet finished.")

    // Delete the workspace (effectively marking the job as expired)
    AsyncPlatform.deleteJob(jobID)

    return RpcJobs.PostRpcJobsExpireResponse.respond204()
  }
}