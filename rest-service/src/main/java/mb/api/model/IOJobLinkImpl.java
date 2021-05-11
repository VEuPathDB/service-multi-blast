package mb.api.model;

public class IOJobLinkImpl implements IOJobLink
{
  private String id;
  private int index;

  @Override
  public String getId() {
    return id;
  }

  @Override
  public IOJobLink setId(String id) {
    this.id = id;
    return this;
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public IOJobLink setIndex(int index) {
    this.index = index;
    return this;
  }
}
