package org.veupathdb.service.multiblast.util;

public class Address
{
  private static final String ProtocolDiv = "://";
  private static final String HttpProtocol = "http" + ProtocolDiv;

  public static String http(String value) {
    return value.indexOf(ProtocolDiv) > -1
      ? value
      : HttpProtocol + value;

  }
}
