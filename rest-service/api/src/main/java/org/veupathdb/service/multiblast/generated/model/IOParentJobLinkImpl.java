package org.veupathdb.service.multiblast.generated.model;

public class IOParentJobLinkImpl implements IOParentJobLink
{
  private String id;
  private int index;

  @Override
  public String getId() {
    return id;
  }

  @Override
  public IOParentJobLink setId(String id) {
    this.id = id;
    return this;
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public IOParentJobLink setIndex(int index) {
    this.index = index;
    return this;
  }
}
