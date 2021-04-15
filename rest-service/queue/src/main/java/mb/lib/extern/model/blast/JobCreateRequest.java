package mb.lib.extern.model.blast;

import mb.lib.extern.model.CreateRequest;

public class JobCreateRequest extends CreateRequest<String[]>
{
  public JobCreateRequest(String host, String[] cli) {
    super(host, cli);
  }
}
