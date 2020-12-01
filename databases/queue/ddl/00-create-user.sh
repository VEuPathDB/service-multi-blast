#!/usr/bin/env sh

# Shhhhh.  Super secret passwords in play...

HISTSIZE=0
 mysql --user=root --password="$MYSQL_ROOT_PASSWORD" <<< \
  "CREATE USER IF NOT EXISTS '$QUEUE_DB_USER'@'%' IDENTIFIED WITH caching_sha2_password BY '$QUEUE_DB_PASS';"
