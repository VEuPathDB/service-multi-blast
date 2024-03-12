.PHONY: local-build
local-build:
	@docker compose -f docker/docker-compose.local.yml \
		build \
		--build-arg=GITHUB_USERNAME=$(GITHUB_USERNAME) \
		--build-arg=GITHUB_TOKEN=$(GITHUB_TOKEN)

.PHONY: local-up
local-up:
	@docker compose -f docker/docker-compose.local.yml up
