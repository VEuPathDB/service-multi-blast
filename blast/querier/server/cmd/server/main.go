package main

import (
	"net/http"
	"strconv"
	"time"

	"github.com/gorilla/mux"
	"github.com/prometheus/client_golang/prometheus/promhttp"
	"github.com/sirupsen/logrus"
	"github.com/x-cray/logrus-prefixed-formatter"
	"server/internal/config"
	"server/internal/server/blast"
)

var version = "development-build"

func init() {
	format := new(prefixed.TextFormatter)
	format.FullTimestamp = true
	format.TimestampFormat = time.RFC3339Nano

	logrus.SetLevel(logrus.DebugLevel)
	logrus.SetFormatter(format)
}

func main() {
	config := config.ParseCLIConfig(version)

	r := mux.NewRouter()
	blast.NewBlastEndpoint(&config).Register("/blast", r)
	r.Handle("/metrics", promhttp.Handler())

	sPort := strconv.FormatUint(uint64(config.Port), 10)
	logrus.Info("Starting server on port " + sPort)
	logrus.Fatal(http.ListenAndServe("0.0.0.0:"+sPort, r))
}
