package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeName("bad-request")
@JsonDeserialize(
    as = BadRequestErrorImpl.class
)
public interface BadRequestError extends Error {
  ErrorType _DISCRIMINATOR_TYPE_NAME = ErrorType.BADREQUEST;

  @JsonProperty("status")
  ErrorType getStatus();

  @JsonProperty("message")
  String getMessage();

  @JsonProperty("message")
  void setMessage(String message);
}
