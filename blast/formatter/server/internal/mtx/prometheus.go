package mtx

import (
	"github.com/prometheus/client_golang/prometheus"
	"github.com/prometheus/client_golang/prometheus/promauto"
)

var (
	jobTimes = promauto.NewHistogram(prometheus.HistogramOpts{
		Name: "format_times",
		Help: "Formatter job durations in seconds.",
		Buckets: []float64{
			1,
			2.5,
			5,
			10,
			15,
			30,
			60,
			120,
		},
	})

	jobFailures = promauto.NewCounter(prometheus.CounterOpts{
		Name: "formatter_errors",
		Help: "Number of formatter jobs that failed.",
	})
)

func RecordJobTime(time float64) {
	jobTimes.Observe(time)
}

func RecordFailedJob() {
	jobFailures.Inc()
}
