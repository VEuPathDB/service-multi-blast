package mb.api.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import mb.api.model.blast.IOBlastConfig;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOJsonJobRequestImpl implements IOJsonJobRequest
{
  private String           site;
  private Set<IOJobTarget> targets;
  private IOBlastConfig    config;
  private String           description;
  private Integer          maxResults;
  private Long             maxResultSize;
  private Byte             maxSequences;
  private boolean          isPrimary = true;

  @Override
  public String getSite() {
    return this.site;
  }

  @Override
  public void setSite(String site) {
    this.site = site;
  }

  @Override
  public Set<IOJobTarget> getTargets() {
    return targets;
  }

  @Override
  public void setTargets(Set<IOJobTarget> targets) {
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

  @Override
  public Byte getMaxSequences() {
    return maxSequences;
  }

  @Override
  public void setMaxSequences(Byte b) {
    this.maxSequences = b;
  }

  @Override
  public boolean getIsPrimary() {
    return isPrimary;
  }

  @Override
  public void setIsPrimary(boolean val) {
    this.isPrimary = val;
  }
}
