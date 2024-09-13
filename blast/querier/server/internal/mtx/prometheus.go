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

	blastJobsHandled = promauto.NewCounter(prometheus.CounterOpts{
		Name: "blast_count",
		Help: "Total number of blast jobs run.",
	})

	blastJobTimes = promauto.NewHistogram(prometheus.HistogramOpts{
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
			1200, // 20 Minutes
			1800, // 30 Minutes
		},
	})

	blastJobFailures = promauto.NewCounter(prometheus.CounterOpts{
		Name: "blast_errors",
		Help: "Total number of blast commands that exited with an error code.",
	})

	diamondJobsHandled = promauto.NewCounter(prometheus.CounterOpts{
		Name: "diamond_count",
		Help: "Total number of diamond jobs run.",
	})

	diamondJobTimes = promauto.NewHistogram(prometheus.HistogramOpts{
		Name: "diamond_times",
		Help: "Diamond job durations in seconds.",
		Buckets: []float64{
			10,   // 10 Seconds
			30,   // 30 Seconds
			60,   // 1 Minute
			150,  // 2.5 Minutes
			300,  // 5 Minutes
			600,  // 10 Minutes
			900,  // 15 Minutes
			1200, // 20 Minutes
			1800, // 30 Minutes
		},
	})

	diamondJobFailures = promauto.NewCounter(prometheus.CounterOpts{
		Name: "diamond_errors",
		Help: "Total number of diamond commands that exited with an error code.",
	})
)

func RecordBlastTime(time float64) {
	blastJobsHandled.Inc()
	blastJobTimes.Observe(time)
}

func RecordDiamondTime(time float64) {
	diamondJobsHandled.Inc()
	diamondJobTimes.Observe(time)
}

func RecordBlastFailure() {
	blastJobFailures.Inc()
}

func RecordDiamondFailure() {
	diamondJobFailures.Inc()
}

func RecordRequest() {
	reqsHandled.Inc()
}
