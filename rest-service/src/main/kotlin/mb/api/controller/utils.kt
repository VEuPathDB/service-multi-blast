package mb.api.controller

import org.veupathdb.lib.container.jaxrs.model.User
import org.veupathdb.lib.container.jaxrs.providers.UserProvider
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.InternalServerErrorException
import jakarta.ws.rs.WebApplicationException
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.model.User.BearerTokenUser
import org.veupathdb.lib.container.jaxrs.providers.OAuthProvider
import org.veupathdb.lib.hash_id.HashID

private const val ErrNoUser = "request reached authenticated endpoint with no user attached"

fun noUserExcept(): RuntimeException = InternalServerErrorException(ErrNoUser)

fun ContainerRequest.requireUser(): User {
  return UserProvider.lookupUser(this).orElseGet {
    // This is a hack to get bearer token from a passed cookie instead of the normally acceptable ways.
    // In the future, the multi-blast client code should be sending an "access-token" query param with requests
    // where it cannot send the Authorization header value.  Cookie should be a last resort.
    val token = cookies["Authorization"]?.value ?: throw noUserExcept()
    val tokenObj = OAuthProvider.getOAuthClient().getValidatedEcdsaSignedToken(OAuthProvider.getOAuthUrl(), token)
    BearerTokenUser(OAuthProvider.getOAuthClient(), OAuthProvider.getOAuthUrl(), tokenObj)
  }
}

inline fun ContainerRequest.requireUserID(): Long = requireUser().userId

inline fun <T> enforceBody(value: T?): T =
  value ?: throw BadRequestException("Request missing one or more input body fields.")

inline fun <R> errorWrap(fn: () -> R): R = try { fn() }
  catch (e: Throwable) { throw if (e is WebApplicationException) e else InternalServerErrorException(e) }

fun hashIDorThrow(raw: String, fn: () -> Exception): HashID {
  try {
    return HashID(raw)
  } catch (e: Exception) {
    throw fn()
  }
}
