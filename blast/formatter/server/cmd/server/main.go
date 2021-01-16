package main

import (
	"net/http"
	"os"
	"time"

	"github.com/gorilla/mux"
	"github.com/sirupsen/logrus"
	"github.com/vulpine-io/midl-layers/request-id/short-id/v1/pkg/midlid"
	"github.com/vulpine-io/midl/v1/pkg/midl"
	"github.com/x-cray/logrus-prefixed-formatter"

	"server/internal/server"
)

const (
	basePath = "/translate/{jobHash}"
)

func init() {
	format := new(prefixed.TextFormatter)
	format.FullTimestamp = true
	format.TimestampFormat = time.RFC3339Nano

	logrus.SetLevel(logrus.DebugLevel)
	logrus.SetFormatter(format)
}

func main() {

	conf := server.GetConfig()

	conf.WSMountPath = os.Getenv("JOB_MOUNT_PATH")
	conf.DBMountPath = os.Getenv("DB_MOUNT_PATH")

	if len(conf.DBMountPath) == 0 || len(conf.WSMountPath) == 0 {
		panic("Missing required environment variable(s) JOB_MOUNT_PATH and/or DB_MOUNT_PATH")
	}

	router := mux.NewRouter()

	router.Handle(basePath + "/6", midl.StreamAdapter(
		"application/zip",
		midl.DefaultJSONErrorSerializer()).
		AddHandlers(
			midlid.NewRequestIdProvider(),
			midl.MiddlewareFunc(server.RunReport6Endpoint))).
		Methods(http.MethodPost)
	router.Handle(basePath + "/7", midl.StreamAdapter(
		"application/zip",
		midl.DefaultJSONErrorSerializer()).
		AddHandlers(
			midlid.NewRequestIdProvider(),
			midl.MiddlewareFunc(server.RunReport7Endpoint))).
		Methods(http.MethodPost)
	router.Handle(basePath + "/10", midl.StreamAdapter(
		"application/zip",
		midl.DefaultJSONErrorSerializer()).
		AddHandlers(
			midlid.NewRequestIdProvider(),
			midl.MiddlewareFunc(server.RunReport10Endpoint))).
		Methods(http.MethodPost)
	router.Handle(basePath + "/17", midl.StreamAdapter(
		"application/zip",
		midl.DefaultJSONErrorSerializer()).
		AddHandlers(
			midlid.NewRequestIdProvider(),
			midl.MiddlewareFunc(server.RunReport17Endpoint))).
		Methods(http.MethodPost)
	router.Handle(basePath + "/{reportType}", midl.StreamAdapter(
		"application/zip",
		midl.DefaultJSONErrorSerializer()).
		AddHandlers(
			midlid.NewRequestIdProvider(),
			midl.MiddlewareFunc(server.RunReportEndpoint))).
		Methods(http.MethodPost)

	logrus.Info("Starting server on port 80")
	logrus.Fatal(http.ListenAndServe("0.0.0.0:80", router))
}
