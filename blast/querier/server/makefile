VERSION=$(shell git describe --tags 2>/dev/null || echo "snapshot")
FILES=$(shell find . -name '*.go')

.PHONY: build
build:
	@CGO_ENABLED=0 GOOS=linux go build -o server cmd/server/main.go
