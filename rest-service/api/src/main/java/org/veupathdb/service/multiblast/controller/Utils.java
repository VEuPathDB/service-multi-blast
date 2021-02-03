package org.veupathdb.service.multiblast.controller;

import javax.ws.rs.InternalServerErrorException;

class Utils
{
  private static final String
    ErrNoUser = "request reached authenticated endpoint with no user attached";

  public static RuntimeException noUserExcept() {
    return new InternalServerErrorException(ErrNoUser);
  }
}
