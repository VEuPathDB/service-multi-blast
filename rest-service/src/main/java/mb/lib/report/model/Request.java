package mb.lib.report.model;

import mb.lib.queue.model.CreateRequest;

public class Request extends CreateRequest<ReportPayload>
{
  public Request(String host, ReportPayload payload) {
    super(host, payload);
  }
}
