package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOShortJobResponseImpl implements IOShortJobResponse
{
  private String      id;
  private String      description;
  private IOJobStatus status;
  private String      created;
  private String      expires;
  private Long        maxResultSize;

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public IOShortJobResponse setId(String id) {
    this.id = id;
    return this;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public IOShortJobResponse setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public IOJobStatus getStatus() {
    return this.status;
  }

  @Override
  public IOShortJobResponse setStatus(IOJobStatus status) {
    this.status = status;
    return this;
  }

  @Override
  public String getCreated() {
    return created;
  }

  @Override
  public IOShortJobResponse setCreated(String created) {
    this.created = created;
    return this;
  }

  @Override
  public String getExpires() {
    return expires;
  }

  @Override
  public IOShortJobResponse setExpires(String expires) {
    this.expires = expires;
    return this;
  }

  @Override
  public Long getMaxResultSize() {
    return maxResultSize;
  }

  @Override
  public IOShortJobResponseImpl setMaxResultSize(Long maxResultSize) {
    this.maxResultSize = maxResultSize;
    return this;
  }
}
