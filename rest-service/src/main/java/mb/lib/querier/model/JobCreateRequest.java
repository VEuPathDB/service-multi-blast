package mb.lib.querier.model;

import mb.lib.queue.model.CreateRequest;

public class JobCreateRequest extends CreateRequest<String[]>
{
  public JobCreateRequest(String host, String[] cli) {
    super(host, cli);
  }
}
