#!/usr/bin/env sh

# Shhhhh.  Super secret passwords in play...

expect <<EOS
  regsub -all -expanded -- "'" \$env(DB_PASS) "''" dbPass

  spawn mysql --user=root --password
  expect "Enter*"
  send "\$env(MYSQL_ROOT_PASSWORD)\r"
  expect "mysql>"
  send "CREATE USER IF NOT EXISTS '\$env(DB_USER)' IDENTIFIED BY '\$dbPass';\r"
  send "\\\\q\\r"
  exit
EOS