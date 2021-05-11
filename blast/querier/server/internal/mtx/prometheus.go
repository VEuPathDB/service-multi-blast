package mtx

import (
	"github.com/prometheus/client_golang/prometheus"
	"github.com/prometheus/client_golang/prometheus/promauto"
)

var (
	reqsHandled = promauto.NewCounter(prometheus.CounterOpts{
		Name: "request_count",
		Help: "Total number of requests handled.",
	})
	jobsHandled = promauto.NewCounter(prometheus.CounterOpts{
		Name: "blast_count",
		Help: "Total number of blast jobs run.",
	})
	jobTimes = promauto.NewHistogram(prometheus.HistogramOpts{
		Name: "blast_times",
		Help: "Blast job durations in seconds.",
		Buckets: []float64{
			10,   // 10 Seconds
			30,   // 30 Seconds
			60,   // 1 Minute
			150,  // 2.5 Minutes
			300,  // 5 Minutes
			600,  // 10 Minutes
			900,  // 15 Minutes
		},
	})
	jobFailures = promauto.NewCounter(prometheus.CounterOpts{
		Name: "blast_errors",
		Help: "Total number of blast commands that exited with an error code.",
	})
)

func RecordJobTime(time float64) {
	jobsHandled.Inc()
	jobTimes.Observe(time)
}

func RecordFailedJob() {
	jobFailures.Inc()
}

func RecordRequest() {
	reqsHandled.Inc()
}
