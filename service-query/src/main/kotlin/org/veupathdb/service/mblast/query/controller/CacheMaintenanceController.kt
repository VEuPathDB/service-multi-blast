package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Context
import org.apache.logging.log4j.LogManager
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.lib.compute.platform.model.JobReference
import org.veupathdb.service.mblast.query.ServiceOptions
import org.veupathdb.service.mblast.query.generated.model.UnauthorizedErrorImpl
import org.veupathdb.service.mblast.query.generated.resources.MaintenanceCache

class CacheMaintenanceController(@Context request: ContainerRequest) : ControllerBase(request), MaintenanceCache {

  private val log = LogManager.getLogger(javaClass)

  override fun deleteMaintenanceCache(adminAuth: String?): MaintenanceCache.DeleteMaintenanceCacheResponse {
    if (adminAuth != ServiceOptions.adminAuthKey)
      return MaintenanceCache.DeleteMaintenanceCacheResponse
        .respond401WithApplicationJson(UnauthorizedErrorImpl().apply {
          message = "unauthorized"
        })

    AsyncPlatform.listJobReferences()
      .asSequence()
      .filter(JobReference::owned)
      .filter { AsyncPlatform.getJob(it.jobID)!!.status != JobStatus.Expired }
      .forEach {
        try {
          AsyncPlatform.expireJob(it.jobID)
        } catch (e: Throwable) {
          log.error("Failed to expire job ${it.jobID}!", e)
        }
      }

    return MaintenanceCache.DeleteMaintenanceCacheResponse.respond204()
  }

  override fun deleteMaintenanceCacheByJobId(
    jobId: String,
    adminAuth: String?,
  ): MaintenanceCache.DeleteMaintenanceCacheByJobIdResponse {
    if (adminAuth != ServiceOptions.adminAuthKey)
      return MaintenanceCache.DeleteMaintenanceCacheByJobIdResponse
        .respond401WithApplicationJson(UnauthorizedErrorImpl().apply {
          message = "unauthorized"
        })

    val jobID = jobId.toHashIDOr404()

    AsyncPlatform.getJob(jobID) ?: throw NotFoundException()
    AsyncPlatform.expireJob(jobID)

    return MaintenanceCache.DeleteMaintenanceCacheByJobIdResponse.respond204()
  }
}