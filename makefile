C_BLUE := "\\033[94m"
C_NONE := "\\033[0m"
C_CYAN := "\\033[36m"
C_RED  := "\\033[91m"


.PHONY: local-build
local-build:
	@docker compose -f docker-compose.local.yml \
		build \
		--build-arg=GITHUB_USERNAME=$(GITHUB_USERNAME) \
		--build-arg=GITHUB_TOKEN=$(GITHUB_TOKEN)

.PHONY: local-up
local-up: __require_env __require_ssh_sock
	@docker compose -f docker-compose.local.yml -f docker-compose.ssh.yml up

.PHONY: local-down
local-down: __require_env
	@docker compose -f docker-compose.local.yml -f docker-compose.ssh.yml down -v


#


.PHONY: __require_env
__require_env:
	@if [ ! -f '.env' ]; then echo; echo "$(C_RED)No .env file found!$(C_NONE)"; echo; exit 1; fi

.PHONY: __require_ssh_sock
__require_ssh_sock:
	@if [ -z "$${SSH_AUTH_SOCK}" ]; then echo; echo "$(C_RED)\$$SSH_AUTH_SOCK is unset, is ssh-agent running?$(C_NONE)"; echo; exit 1; fi

