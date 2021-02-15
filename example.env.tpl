# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#  Postgres Service DB Configuration
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

# <required> Postgres root user password
#
# Used by containers:
#   - service-db
POSTGRES_PASSWORD=

# [optional] Postgres service-user username
#
# Used by containers:
#   - service-db
#   - service
SVC_DB_USER=service

# <required> Postgres service-user password
#
# Used by containers:
#   - service-db
#   - service
SVC_DB_PASS=

# [optional] Postgres container hostname/alias
#
# Used by containers:
#   - service
SVC_DB_HOST=service-db

# [optional] Postgres port
#
# Used by containers:
#   - service
SVC_DB_PORT=5432

# [optional] Postgres database name
#
# Used by containers:
#   - service
SVC_DB_NAME=postgres


# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#  Blast Configuration
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

# [optional] Blast job workspace directory (mount path)
#
# Used by containers:
#   - blast
#   - service
MOUNT_PATH=/jobs

# [optional] Blast container hostname/alias
#
# Used by containers:
#   - service
BLAST_HOST=blast


# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#  MySQL Queue DB Configuration
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

# <required> MySQL root user password
#
# Used by containers:
#   - queue-db
MYSQL_ROOT_PASSWORD=

# [optional] MySQL queue-user username
#
# Used by containers:
#   - queue-db
QUEUE_DB_USER=queue

# <required> MySQL queue-user password
#
# Used by containers:
#   - queue-db
QUEUE_DB_PASS=


# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#  Queue Configuration
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

# <required> Queue DB connection string.
#
# Specifies a data source name for the job queue and the repository
# database in the form: ${QUEUE_DB_USER}:${QUEUE_DB_PASS}@tcp(queue-db:3306)/queue
#
# Used by containers:
#   - queue
FIREWORQ_MYSQL_DSN=

# [optional] Job queue container hostname/alias
#
# Used by containers:
#   - service
QUEUE_HOST=queue

# [optional] Name of the route used to add jobs to the default queue.
#
# Used by containers:
#   - queue
#   - service
QUEUE_ROUTE=blast

# [optional] Queue worker pool size
#
# Used by containers:
#   - queue
FIREWORQ_QUEUE_DEFAULT_MAX_WORKERS=2

# [optional] Default job queue for new blast jobs
#
# Used by containers:
#   - queue
#   - service
FIREWORQ_QUEUE_DEFAULT=blast-jobs

# [optional] The time in milliseconds between new job polls.
#
# Used by containers:
#   - queue
FIREWORQ_QUEUE_DEFAULT_POLLING_INTERVAL=1000

# [optional] The host/port the queue server should bind to in it's container.
#
# Used by containers:
#   - queue
FIREWORQ_BIND=0.0.0.0:80

# [optional] Queue DB driver name
#
# Used by containers:
#   - queue
FIREWORQ_DRIVER=mysql
