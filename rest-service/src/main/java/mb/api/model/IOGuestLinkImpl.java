package mb.api.model;

public class IOGuestLinkImpl implements IOGuestLink
{
  private long guestID;

  @Override
  public long getGuestID() {
    return guestID;
  }

  @Override
  public void setGuestID(long guestID) {
    this.guestID = guestID;
  }
}
