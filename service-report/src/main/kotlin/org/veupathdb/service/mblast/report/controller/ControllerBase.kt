package org.veupathdb.service.mblast.report.controller

import jakarta.ws.rs.NotFoundException
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.providers.UserProvider
import org.veupathdb.lib.hash_id.HashID

/**
 * Base functionality for controllers.
 */
sealed class ControllerBase(protected val request: ContainerRequest) {

  /**
   * User ID associated with the current request.
   */
  protected val userID by lazy { UserProvider.lookupUser(request).orElseThrow().userID }

  /**
   * Auth Header Value
   */
  protected val authHeader by lazy { UserProvider.getSubmittedAuth(request).orElseThrow()!! }

  /**
   * Convert the target string to a [HashID] instance or throw a 404 if the
   * string is not a valid MD5 hash.
   */
  protected fun String.toHashIDOr404() =
    try { HashID(this) } catch (e: IllegalArgumentException) { throw NotFoundException() }
}