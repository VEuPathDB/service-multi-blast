package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOJsonJobRequestImpl implements IOJsonJobRequest
{
  private String        site;
  private IOJobTarget[] targets;
  private IOBlastConfig config;
  private String        description;
  private Integer       maxResults;
  private Long          maxResultSize;

  @Override
  public String getSite() {
    return this.site;
  }

  @Override
  public void setSite(String site) {
    this.site = site;
  }

  @Override
  public IOJobTarget[] getTargets() {
    return targets;
  }

  @Override
  public void setTargets(IOJobTarget[] targets) {
    this.targets = targets;
  }

  @Override
  public IOBlastConfig getConfig() {
    return this.config;
  }

  @Override
  public void setConfig(IOBlastConfig config) {
    this.config = config;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public Integer getMaxResults() {
    return this.maxResults;
  }

  @Override
  public void setMaxResults(Integer maxResults) {
    this.maxResults = maxResults;
  }

  @Override
  public Long getMaxResultSize() {
    return maxResultSize;
  }

  @Override
  public void setMaxResultSize(Long maxResultSize) {
    this.maxResultSize = maxResultSize;
  }
}
