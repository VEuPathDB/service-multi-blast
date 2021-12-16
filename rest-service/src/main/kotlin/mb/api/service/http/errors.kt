package mb.api.service.http

import jakarta.ws.rs.InternalServerErrorException
import jakarta.ws.rs.WebApplicationException


fun Exception.wrap(): WebApplicationException =
  if (this is WebApplicationException) this else InternalServerErrorException(this)
