package server

type Config struct {
	DBMountPath string
	WSMountPath string
}

var config Config

func GetConfig() *Config {
	return &config
}
