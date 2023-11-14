package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.NotFoundException
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.model.User
import org.veupathdb.lib.container.jaxrs.providers.UserProvider
import org.veupathdb.lib.hash_id.HashID

/**
 * Base functionality for controllers.
 */
sealed class ControllerBase(protected val request: ContainerRequest) {

  protected val optUser: User? by lazy { UserProvider.lookupUser(request).orElse(null) }

  protected val user: User by lazy { UserProvider.lookupUser(request).orElseThrow() }

  /**
   * User ID associated with the current request.
   */
  protected val userID by lazy { user.userID }

  protected val optUserID by lazy { optUser?.userID }

  /**
   * Convert the target string to a [HashID] instance or throw a 404 if the
   * string is not a valid MD5 hash.
   */
  protected fun String.toHashIDOr404() =
    try { HashID(this) } catch (e: Throwable) { throw NotFoundException() }
}