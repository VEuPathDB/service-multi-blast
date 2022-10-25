package org.veupathdb.service.mblast.query.service.jobs.submit

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response
import org.veupathdb.service.mblast.query.service.jobs.submit.model.JobSubmission

fun _handleJobExistsAndIsLinked(sub: JobSubmission) {
  throw WebApplicationException(Response.Status.CONFLICT)
}