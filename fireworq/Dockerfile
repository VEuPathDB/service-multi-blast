FROM alpine:3

ARG SETUP_UTIL_URL=https://github.com/VEuPathDB/util-fireworq-init/releases/download/v1.4.1/queue-setup.v1.4.1.x64.tar.gz
ARG FIREWORQ_URL=https://github.com/fireworq/fireworq/releases/download/v1.4.1/fireworq_linux_amd64.zip

# Install utils, fetch fireworq, fetch queue setup
RUN apk add --no-cache wget tar zip \
    && wget ${FIREWORQ_URL} -O fireworq.zip \
    && wget ${SETUP_UTIL_URL} -O setup.tar.gz \
    && unzip -j fireworq.zip fireworq -d /usr/local/bin && rm fireworq.zip \
    && tar -xf setup.tar.gz -C /usr/local/bin && rm setup.tar.gz

COPY queues.yml queues.yml

CMD ["sh", "-c", "while ! nc -z queue-db 3306; do echo \"Waiting for MySQL...\"; sleep 5s; done; entrypoint"]
