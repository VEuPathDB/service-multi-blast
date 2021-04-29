package config

import (
	"fmt"
	"os"

	cli "github.com/Foxcapades/Argonaut/v0"
	"github.com/Foxcapades/Argonaut/v0/pkg/argo"
)

const (
	descDbDir = "Root directory this service will use as the base path when" +
		" configuring a blast tool run.\n\n" +
		"This should match the volume mount point set when starting the docker" +
		" container.\n\n" +
		"Defaults to /db"
	descOutDir = "Output directory this service will use as the base path when" +
		" configuring a blast tool run.\n\n" +
		"This should match the volume mount point set when starting the docker" +
		" container.\n\n" +
		"Defaults to /out"
	descPort = "Port the HTTP server should bind to."
	descVers = "Prints the server version."
)

type Config struct {
	Port    uint16
	OutDir  string
	DBDir   string
	Version string
}

func ParseCLIConfig(version string) Config {
	config := Config{}

	cli.NewCommand().
		Flag(cli.NewFlag().
			Short('d').
			Long("db-dir").
			Description(descDbDir).
			Arg(cli.NewArg().
				Bind(&config.DBDir).
				Require().
				Name("path").
				Default(os.Getenv("DB_MOUNT_PATH")))).
		Flag(cli.NewFlag().
			Short('o').
			Long("out-dir").
			Description(descOutDir).
			Arg(cli.NewArg().
				Bind(&config.OutDir).
				Require().
				Name("path").
				Default(os.Getenv("JOB_MOUNT_PATH")))).
		Flag(cli.NewFlag().
			Short('p').
			Long("port").
			Description(descPort).
			Arg(cli.NewArg().
				Bind(&config.Port).
				Require().
				Name("port").
				Default(80))).
		Flag(cli.NewFlag().
			Short('V').
			Long("version").
			Description(descVers).
			OnHit(func(argo.Flag) {
				fmt.Println(config.Version)
				os.Exit(0)
			})).
		MustParse()

	config.Version = version
	return config
}