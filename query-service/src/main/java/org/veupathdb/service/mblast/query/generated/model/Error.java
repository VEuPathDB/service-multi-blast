package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "status"
)
@JsonSubTypes({
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.ServerError.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.ForbiddenError.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.UnprocessableEntityError.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.NotFoundError.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.UnauthorizedError.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.MethodNotAllowedError.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.BadRequestError.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.Error.class)
})
@JsonDeserialize(
    as = ErrorImpl.class
)
public interface Error {
  ErrorType _DISCRIMINATOR_TYPE_NAME = null;

  @JsonProperty("status")
  ErrorType getStatus();

  @JsonProperty("message")
  String getMessage();

  @JsonProperty("message")
  void setMessage(String message);
}
