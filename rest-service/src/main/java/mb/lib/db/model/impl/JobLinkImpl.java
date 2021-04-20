package mb.lib.db.model.impl;

import java.util.Objects;

import mb.lib.db.model.JobLink;
import mb.lib.model.HashID;

public class JobLinkImpl implements JobLink
{
  private final HashID jobHash;
  private final HashID parentHash;
  private final int    position;

  public JobLinkImpl(HashID jobHash, HashID parentHash, int position) {
    this.jobHash    = jobHash;
    this.parentHash = parentHash;
    this.position   = position;
  }

  @Override
  public HashID childID() {
    return jobHash;
  }

  @Override
  public HashID parentID() {
    return parentHash;
  }

  @Override
  public int position() {
    return position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JobLinkImpl jobLink = (JobLinkImpl) o;
    return jobHash.equals(jobLink.jobHash) && parentHash.equals(jobLink.parentHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobHash, parentHash);
  }
}
