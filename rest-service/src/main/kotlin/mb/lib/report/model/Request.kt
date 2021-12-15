package mb.lib.report.model

import mb.lib.queue.model.CreateRequest

class Request(host: String, payload: ReportPayload?) : CreateRequest<ReportPayload?>(host, payload)