FROM golang:alpine3.12 AS build

COPY server /server
WORKDIR /server
RUN CGO_ENABLED=0 GOOS=linux go build -o server cmd/server/main.go
RUN pwd && ls

FROM ubuntu:22.04 AS run

# Setup timezone and dependencies
RUN apt-get update --fix-missing \
    && apt-get install -y tzdata wget \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* \
    && cp /usr/share/zoneinfo/America/New_York /etc/localtime \
    && echo "America/New_York" > /etc/timezone

# Install blast binaries
RUN wget https://ftp.ncbi.nlm.nih.gov/blast/executables/blast+/2.10.1/ncbi-blast-2.10.1+-x64-linux.tar.gz -O blast.tgz \
    && tar -xzf blast.tgz \
    && mv ncbi-blast-2.10.1+ blast \
    && rm blast.tgz

# Install diamond binaries
RUN mkdir -p /diamond/bin \
    && cd /diamond/bin \
    && wget https://github.com/bbuchfink/diamond/releases/download/v2.1.9/diamond-linux64.tar.gz -O diamond.tgz \
    && tar -xzf diamond.tgz \
    && rm diamond.tgz

ENV BLASTDB=/:/db:/blast/blastdb:/blast/blastdb_custom

EXPOSE 80

COPY --from=build /server/server /server

CMD /server
