#!/usr/bin/env sh

init() {
  echo "Waiting for Fireworq"
  while ! nc -z localhost 80; do
    printf '.'
    sleep 1s
  done
  echo "Fireworq online"

  echo "Creating job route"
  cat category-init.json | envsubst | curl -s -X"PUT" -d"@-" localhost/routing/${JOB_CATEGORY}
}

db() {
  echo "Waiting for MySQL"
  while ! nc -z queue-db 3306; do
    printf '.'
    sleep 1s
  done
  echo "MySQL online"
}

db
init &
echo "$@"
DEBUG=1 $@

