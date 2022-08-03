FROM mysql:8-debian

RUN apt-get update \
 && apt-get install -y expect \
 && rm -rf /var/lib/apt/lists*

COPY ./ddl /docker-entrypoint-initdb.d/
