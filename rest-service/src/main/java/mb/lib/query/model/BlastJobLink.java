package mb.lib.query.model;

import mb.lib.model.HashID;

public class BlastJobLink
{
  private HashID childJobID;
  private HashID parentJobID;
  private int    position;

  public HashID getChildJobID() {
    return childJobID;
  }

  public BlastJobLink setChildJobID(HashID childJobID) {
    this.childJobID = childJobID;
    return this;
  }

  public HashID getParentJobID() {
    return parentJobID;
  }

  public BlastJobLink setParentJobID(HashID parentJobID) {
    this.parentJobID = parentJobID;
    return this;
  }

  public int getPosition() {
    return position;
  }

  public BlastJobLink setPosition(int position) {
    this.position = position;
    return this;
  }

  @Override
  public String toString() {
    return "BlastJobLink{childID=" + childJobID + ", parentID=" + parentJobID + "}";
  }
}
