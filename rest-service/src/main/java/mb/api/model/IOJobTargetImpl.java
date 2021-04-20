package mb.api.model;

import java.util.Objects;

public class IOJobTargetImpl implements IOJobTarget
{
  private String organism;
  private String target;

  public IOJobTargetImpl(String organism, String target) {
    this.organism = organism;
    this.target   = target;
  }

  public IOJobTargetImpl() {
  }

  @Override
  public String organism() {
    return organism;
  }

  @Override
  public IOJobTarget organism(String org) {
    organism = org;
    return this;
  }

  @Override
  public String target() {
    return target;
  }

  @Override
  public IOJobTarget target(String tgt) {
    target = tgt;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IOJobTargetImpl that = (IOJobTargetImpl) o;
    return Objects.equals(organism, that.organism) && Objects.equals(target, that.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(organism, target);
  }
}
