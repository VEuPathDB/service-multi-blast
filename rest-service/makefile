APP_PACKAGE  := $(shell ./gradlew -q print-package)
PWD          := $(shell pwd)
MAIN_DIR     := src/main/java/$(shell echo $(APP_PACKAGE) | sed 's/\./\//g')
TEST_DIR     := $(shell echo $(MAIN_DIR) | sed 's/main/test/')
GEN_DIR      := $(MAIN_DIR)/generated
ALL_PACKABLE := $(shell find src/main -type f)

GRADLE := $(shell if [ -f './gradlew' ]; then echo './gradlew'; else echo 'gradle'; fi)

EXAMPLE_DIR      := src/main/java/org/veupathdb/service/demo
EXAMPLE_TEST_DIR := src/test/java/org/veupathdb/service/demo

C_BLUE := "\\033[94m"
C_NONE := "\\033[0m"
C_CYAN := "\\033[36m"

.PHONY: default
default:
	@echo "no"

.PHONY: compile
compile: install-dev-env
	@./gradlew clean compileJava

.PHONY: test
test:
	@./gradlew clean test

.PHONY: jar
jar: build/libs/service.jar

.PHONY: docker
docker:
	@docker build \
		-t $(shell ./gradlew -q print-container-name) \
		--build-arg=GITHUB_USERNAME=$(GITHUB_USERNAME) \
		--build-arg=GITHUB_TOKEN=$(GITHUB_TOKEN) \
		.

gen-jaxrs: api.raml merge-raml
	@echo "JAX-RS code generation disabled for this project."

gen-docs: node_modules/.bin/redocly
	@node_modules/.bin/redocly build-docs openapi.yml -o ../docs/api.html
	@cp ../docs/api.html src/main/resources/api.html

merge-raml:
	@$(GRADLE) merge-raml -vv schema > schema/library.raml

#
# File based targets
#

build/libs/service.jar: build.gradle.kts service.properties
	@echo "$(C_BLUE)Building application jar$(C_NONE)"
	@$(GRADLE) clean test shadowJar
