package mb.lib.extern.model.format;

import mb.lib.extern.model.CreateRequest;

public class JobCreateRequest extends CreateRequest<JobPayload>
{
  public JobCreateRequest(String host, JobPayload payload) {
    super(host, payload);
  }
}
