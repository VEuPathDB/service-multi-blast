package mb.lib.db.model.impl;

import java.util.Arrays;
import java.util.Objects;

import mb.lib.db.model.JobLink;

public class JobLinkImpl implements JobLink
{
  private final byte[] jobHash;
  private final byte[] parentHash;
  private final int position;

  public JobLinkImpl(byte[] jobHash, byte[] parentHash, int position) {
    this.jobHash    = jobHash;
    this.parentHash = parentHash;
    this.position   = position;
  }

  @Override
  public byte[] jobHash() {
    return jobHash;
  }

  @Override
  public byte[] parentHash() {
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
    return position == jobLink.position
      && Arrays.equals(jobHash, jobLink.jobHash)
      && Arrays.equals(parentHash, jobLink.parentHash);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(position);
    result = 31 * result + Arrays.hashCode(jobHash);
    result = 31 * result + Arrays.hashCode(parentHash);
    return result;
  }
}
