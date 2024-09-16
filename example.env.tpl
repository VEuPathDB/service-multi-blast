# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# #
# # Core Lib Options
# #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

# User Login Auth (Salt Hash)
AUTH_SECRET_KEY=

# HTTP Server bind port
SERVER_PORT=80

OAUTH_CLIENT_ID=
OAUTH_CLIENT_SECRET=
OAUTH_URL=

ADMIN_AUTH_TOKEN=

# LDAP servers used to fetch DB connection details.
#
# Comma separated list of url:port combinations.
#
# For example: ldap.server1.com:888,ldap.server2.com:898
LDAP_SERVER=

# LDAP Base Distinguished Name
ORACLE_BASE_DN=

# Account DB TNS Name (used for LDAP lookup)
ACCT_DB_TNS_NAME=

# Account DB Username
ACCT_DB_USER=

# Account DB Password
ACCT_DB_PASS=

# User DB TNS Name (used for LDAP lookup)
USER_DB_TNS_NAME=

# User DB Username
USER_DB_USER=

# User DB Password
USER_DB_PASS=

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# #
# # Multiblast Options
# #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

# #
# # Filesystem Config
# #

# Job data workspace mount point.
JOB_MOUNT_PATH=/out

LOCAL_HOST_WORKSPACE_MOUNT_PATH=/home/${USER}/blastout

# Blast file mount point.
DB_MOUNT_PATH=/db

LOCAL_HOST_DB_MOUNT_PATH=/home/${USER}/blastdb

# Site build (used to resolve blast target paths)
DB_BUILD=51

# #
# # Service Hosts
# #

# Docker container name/URL for the Fireworq server.
QUEUE_HOST=queue

# Docker container name/URL for the blast worker node.
BLAST_HOST=blast

# Docker container name/url for the formatter worker node.
FORMATTER_HOST=formatter

# Docker container name/URL for the validation service.
VALIDATOR_HOST=validator

# #
# # Blast Query Job Config
# #

# Name of the queue for blast jobs.
#
# This value should mirror the blast queue's name as set in
# the fireworq/queues.yml file.
BLAST_QUEUE_NAME=blast

# Name of the route/category for blast jobs.
#
# This value should mirror the blast queue's category as set
# in the fireworq/queues.yml file.
BLAST_JOB_CATEGORY=blast

# #
# # Blast Format Job Config
# #

# Name of the queue for formatter jobs.
#
# This value should mirror the formatter queue's name as set
# in the fireworq/queues.yml file.
FORMAT_QUEUE_NAME=format

# Name of the route/category for formatter jobs.
#
# This value should mirror the formatter queue's category as
# set in the fireworq/queues.yml file.
FORMAT_JOB_CATEGORY=format

# #
# # Blast Service Options
# #

# Length of time a job's results will be held after the last
# usage (in days).
JOB_TIMEOUT=5

# Maximum number of sequences that may be submitted in a
# single query.
MAX_QUERIES_PER_REQUEST=100

# Maximum number of results that a user is allowed to
# request in a single job.  This is not a hard rule, as
# there is no way to guarantee it.  Instead the number of
# sequences is multiplied by the number of requested hits.
# Additionally requesters may override this value with a
# lower limit when submitting a job.
MAX_RESULTS_PER_QUERY=10000

# Maximum allowed total query length in bytes for a single
# job.
MAX_INPUT_QUERY_SIZE=3145728

# Maximum allowed length for an individual nucleotide query
# sequence.
MAX_NA_SEQ_SIZE=1048576

# Maximum allowed length for an individual protein query
# sequence.
MAX_AA_SEQ_SIZE=102400

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# #
# # Fireworq Options
# #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

# Root password for the queue server's mysql database.
#
# This value should only be known by ops, if anyone at all.
MYSQL_ROOT_PASSWORD=

# Queue service DB user (used by the queue server to access
# the database)
QUEUE_DB_USER=queue

# Queue service DB password. (Should only be known by ops).
QUEUE_DB_PASS=

# Specifies a data source name for the job queue and the
# repository database in the form:
#   user:password@tcp(mysql_host:mysql_port)/database?options
#
# The username and password value here must match the
# `QUEUE_DB_USER` and `QUEUE_DB_PASS` value for the queue-db
# container.
FIREWORQ_MYSQL_DSN=

# Default job queue
FIREWORQ_QUEUE_DEFAULT=blast

# Max workers on the default queue
FIREWORQ_QUEUE_DEFAULT_MAX_WORKERS=5

FIREWORQ_BIND=0.0.0.0:80

FIREWORQ_DRIVER=mysql
