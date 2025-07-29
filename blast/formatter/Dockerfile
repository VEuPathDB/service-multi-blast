FROM golang:alpine3.12 AS build

COPY server /server
WORKDIR /server
RUN CGO_ENABLED=0 GOOS=linux go build -o server cmd/server/main.go
RUN pwd && ls

FROM ubuntu:24.04 AS run

# Setup timezone and dependencies
RUN apt-get update --fix-missing \
    && apt-get install -y tzdata wget libgomp1 \
    && apt-get clean \
    && cp /usr/share/zoneinfo/America/New_York /etc/localtime \
    && echo "America/New_York" > /etc/timezone

# Install blast binaries
RUN wget https://ftp.ncbi.nlm.nih.gov/blast/executables/blast+/2.17.0/ncbi-blast-2.17.0+-x64-linux.tar.gz -O blast.tgz \
    && tar -xzf blast.tgz \
    && mv ncbi-blast-2.17.0+ blast \
    && rm blast.tgz

ENV BLASTDB=/:/db:/blast/blastdb:/blast/blastdb_custom

EXPOSE 80

COPY --from=build /server/server /server

CMD /server