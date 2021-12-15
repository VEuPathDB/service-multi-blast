package mb.api.controller

import org.veupathdb.lib.container.jaxrs.model.User
import org.veupathdb.lib.container.jaxrs.providers.UserProvider
import javax.ws.rs.BadRequestException
import javax.ws.rs.InternalServerErrorException
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Request

private const val ErrNoUser = "request reached authenticated endpoint with no user attached"

fun noUserExcept(): RuntimeException = InternalServerErrorException(ErrNoUser)

inline fun Request.requireUser(): User = UserProvider.lookupUser(this).orElseThrow(::noUserExcept)
inline fun Request.requireUserID(): Long = UserProvider.lookupUser(this).orElseThrow(::noUserExcept).userID

inline fun <T> enforceBody(value: T?): T =
  value ?: throw BadRequestException("Request missing one or more input body fields.")
inline fun <T> enforceParam(value: T?, name: String): T =
  value ?: throw BadRequestException("Request missing required parameter \"$name\".")

inline fun <R> errorWrap(fn: () -> R): R = try { fn() }
  catch (e: Throwable) { throw if (e is WebApplicationException) e else InternalServerErrorException(e) }