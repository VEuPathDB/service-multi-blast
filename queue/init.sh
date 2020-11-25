#!/usr/bin/env sh

init() {
  while ! nc -z localhost 80; do
    echo "waiting for fireworq"
    sleep 1s
  done

  cat category-init.json | envsubst | curl -X"PUT" -d"@-" localhost/routing/${QUEUE_ROUTE}
}

init &
echo "$@"
$@

