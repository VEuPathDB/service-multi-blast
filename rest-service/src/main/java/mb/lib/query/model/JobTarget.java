package mb.lib.query.model;

public class JobTarget
{
  private String organism;
  private String target;

  public String getOrganism() {
    return organism;
  }

  public JobTarget setOrganism(String organism) {
    this.organism = organism;
    return this;
  }

  public String getTarget() {
    return target;
  }

  public JobTarget setTarget(String target) {
    this.target = target;
    return this;
  }

  @Override
  public String toString() {
    return "JobTarget{" +
      "organism='" + organism + '\'' +
      ", target='" + target + '\'' +
      '}';
  }
}
