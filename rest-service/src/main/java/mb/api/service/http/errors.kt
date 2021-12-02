package mb.api.service.http

import javax.ws.rs.InternalServerErrorException
import javax.ws.rs.WebApplicationException

fun Exception.wrap(): WebApplicationException =
  if (this is WebApplicationException) this else InternalServerErrorException(this)
