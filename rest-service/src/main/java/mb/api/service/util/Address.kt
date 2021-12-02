package mb.api.service.util

object Address
{
  private const val ProtocolDiv = "://"
  private const val HttpProtocol = "http$ProtocolDiv"

  fun http(value: String) = if (value.indexOf(ProtocolDiv) > -1) value else HttpProtocol + value
}
