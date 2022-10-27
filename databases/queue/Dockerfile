FROM mysql:8.0.31-debian

RUN apt update \
 && apt install -y expect \
 && rm -rf /var/lib/apt/lists*

COPY ./ddl /docker-entrypoint-initdb.d/
