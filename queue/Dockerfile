FROM fireworq/fireworq:1.4 AS tmp
FROM alpine:3.12.1

# Configure queue server
ENV FIREWORQ_QUEUE_DEFAULT="blast"\
    FIREWORQ_QUEUE_DEFAULT_POLLING_INTERVAL="1000"\
    FIREWORQ_QUEUE_DEFAULT_MAX_WORKERS="2"\
    FIREWORQ_BIND=0.0.0.0:80\
    FIREWORQ_DRIVER=mysql\
    QUEUE_ROUTE="blast"

RUN apk add --no-cache curl gettext

COPY --from=tmp /usr/local/bin /usr/local/bin/
COPY ["category-init.json", "init.sh", "./"]
EXPOSE 80
ENTRYPOINT ["/init.sh", "/usr/local/bin/fireworq"]

