#!/usr/bin/env sh

psql -Upostgres <<EOS
  CREATE USER ${SVC_DB_USER} WITH PASSWORD '${SVC_DB_PASS}';
EOS
