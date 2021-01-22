FROM golang:alpine3.12 AS build

COPY server /server
WORKDIR /server
RUN CGO_ENABLED=0 GOOS=linux go build -o server cmd/server/main.go
RUN pwd && ls

FROM ncbi/blast:2.10.1 AS run

COPY --from=build /server/server /server

EXPOSE 80

CMD /server