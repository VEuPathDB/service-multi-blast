.PHONY: local-build
local-build:
	@docker compose -f docker-compose.local.yml \
		build \
		--build-arg=GITHUB_USERNAME=$(GITHUB_USERNAME) \
		--build-arg=GITHUB_TOKEN=$(GITHUB_TOKEN)

.PHONY: local-up
local-up:
	@docker compose -f docker-compose.local.yml -f docker-compose.ssh.yml up

.PHONY: local-down
local-down:
	@docker compose -f docker-compose.local.yml -f docker-compose.ssh.yml down -v
