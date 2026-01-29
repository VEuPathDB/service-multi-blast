package mb.api.controller

import org.veupathdb.lib.container.jaxrs.providers.UserProvider
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.InternalServerErrorException
import jakarta.ws.rs.WebApplicationException
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.hash_id.HashID

private const val ErrNoUser = "request reached authenticated endpoint with no user attached"

fun noUserExcept(): RuntimeException = InternalServerErrorException(ErrNoUser)

fun ContainerRequest.requireUser() =
  UserProvider.lookupUser(this).orElseThrow(::noUserExcept)!!

fun ContainerRequest.requireUserID(): Long = requireUser().userId

fun <T> enforceBody(value: T?): T =
  value ?: throw BadRequestException("Request missing one or more input body fields.")

inline fun <R> errorWrap(fn: () -> R): R = try { fn() }
  catch (e: Throwable) { throw e as? WebApplicationException ?: InternalServerErrorException(e) }

fun hashIDorThrow(raw: String, fn: () -> Exception): HashID {
  try {
    return HashID(raw)
  } catch (_: Exception) {
    throw fn()
  }
}
