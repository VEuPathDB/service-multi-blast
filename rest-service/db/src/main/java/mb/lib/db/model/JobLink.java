package mb.lib.db.model;

public interface JobLink
{
  byte[] jobHash();
  byte[] parentHash();
  int position();
}
