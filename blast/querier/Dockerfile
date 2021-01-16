FROM ncbi/blast:2.10.1

ARG SERVER_VERSION="v0.0.12"
ARG SERVER_REPO="https://github.com/VEuPathDB/util-http2cli"

RUN curl -Lo /tmp/server.tar.gz ${SERVER_REPO}/releases/download/${SERVER_VERSION}/http2cli.linux.${SERVER_VERSION}.x64.tar.gz \
 && tar -xf /tmp/server.tar.gz \
 && mv http2cli /usr/bin/http2cli

EXPOSE 80

CMD http2cli
