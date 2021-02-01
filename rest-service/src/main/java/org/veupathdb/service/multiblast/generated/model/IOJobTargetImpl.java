package org.veupathdb.service.multiblast.generated.model;

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
}
