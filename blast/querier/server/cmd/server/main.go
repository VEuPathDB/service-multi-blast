package main

import (
	"net/http"
	"os"
	"os/signal"
	"strconv"
	"syscall"
	"time"

	"github.com/gorilla/mux"
	"github.com/prometheus/client_golang/prometheus/promhttp"
	"github.com/sirupsen/logrus"
	"github.com/x-cray/logrus-prefixed-formatter"

	"server/internal/config"
	"server/internal/server/blast"
	"server/internal/server/diamond"
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
	conf := config.ParseCLIConfig(version)

	r := mux.NewRouter()

	blast.NewBlastEndpoint(&conf).Register("/blast", r)
	diamond.NewDiamondEndpoint(conf).Register("/diamond", r)

	r.Handle("/metrics", promhttp.Handler())

	sPort := strconv.FormatUint(uint64(conf.Port), 10)

	// Exit signals
	e1 := make(chan os.Signal, 1)
	e2 := make(chan error, 1)

	signal.Notify(e1, os.Interrupt, syscall.SIGTERM)

	go func() {
		logrus.Info("Starting server on port " + sPort)
		e2 <- http.ListenAndServe("0.0.0.0:"+sPort, r)
	}()

	select {
	case <-e1:
		logrus.Info("Shutdown signal received.")
		os.Exit(0)
	case e := <-e2:
		logrus.Fatal(e)
		os.Exit(1)
	}
}
