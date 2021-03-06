APP_PACKAGE  := $(shell ./gradlew -q print-package)
PWD          := $(shell pwd)
MAIN_DIR     := src/main/java/$(shell echo $(APP_PACKAGE) | sed 's/\./\//g')
TEST_DIR     := $(shell echo $(MAIN_DIR) | sed 's/main/test/')
GEN_DIR      := $(MAIN_DIR)/generated
ALL_PACKABLE := $(shell find src/main -type f)
BIN_DIR := .tools/bin

EXAMPLE_DIR      := src/main/java/org/veupathdb/service/demo
EXAMPLE_TEST_DIR := src/test/java/org/veupathdb/service/demo

C_BLUE := "\\033[94m"
C_NONE := "\\033[0m"
C_CYAN := "\\033[36m"

.PHONY: default
default:
	@echo "Please choose one of:"
	@echo ""
	@echo "$(C_BLUE)  make compile$(C_NONE)"
	@echo "    Compiles the existing code in 'src/'.  Regenerates files if the"
	@echo "    api spec has changed."
	@echo ""
	@echo "$(C_BLUE)  make test$(C_NONE)"
	@echo "    Compiles the existing code in 'src/' and runs unit tests."
	@echo "    Regenerates files if the api spec has changed."
	@echo ""
	@echo "$(C_BLUE)  make jar$(C_NONE)"
	@echo "    Compiles a 'fat jar' from this project and it's dependencies."
	@echo ""
	@echo "$(C_BLUE)  make docker$(C_NONE)"
	@echo "    Builds a runnable docker image for this service"
	@echo ""
	@echo "$(C_BLUE)  make install-dev-env$(C_NONE)"
	@echo "    Ensures the current dev environment has the necessary "
	@echo "    installable tools to build this project."
	@echo ""
	@echo "$(C_BLUE)  make gen-jaxrs$(C_NONE)"
	@echo "    Ensures the current dev environment has the necessary "
	@echo "    installable tools to build this project."
	@echo ""

.PHONY: compile
compile: install-dev-env
	@./gradlew clean compileJava

.PHONY: test
test: install-dev-env
	@./gradlew clean test

.PHONY: jar
jar: install-dev-env build/libs/service.jar

.PHONY: docker
docker:
	@docker build -t $(shell ./gradlew -q print-container-name) .

.PHONY: cleanup-example
cleanup-example:
	@$(BIN_DIR)/demo-cleanup.sh $(MAIN_DIR)

.PHONY: install-dev-env
install-dev-env:
	@if [ ! -d .tools ]; then \
		git clone https://github.com/VEuPathDB/lib-jaxrs-container-build-utils .tools; \
	else \
		cd .tools && git pull && cd ..; \
	fi
	@$(BIN_DIR)/check-env.sh
	@$(BIN_DIR)/install-fgputil.sh
	@$(BIN_DIR)/install-oracle.sh

fix-path:
	@$(BIN_DIR)/fix-path.sh $(EXAMPLE_DIR) $(MAIN_DIR)
	@$(BIN_DIR)/fix-path.sh $(EXAMPLE_TEST_DIR) $(TEST_DIR)

gen-jaxrs: api.raml merge-raml
	@echo "JAX-RS code generation disabled for this project."

gen-docs: node_modules/.bin/redoc-cli
	@node_modules/.bin/redoc-cli bundle openapi.yml -o ../docs/api.html
	@cp ../docs/api.html src/main/resources/api.html

.PHONY: merge-raml
merge-raml:
	@$(BIN_DIR)/merge-raml -vv schema > schema/library.raml

#
# File based targets
#

build/libs/service.jar: vendor/fgputil-accountdb-1.0.0.jar  vendor/fgputil-core-1.0.0.jar vendor/fgputil-db-1.0.0.jar vendor/fgputil-web-1.0.0.jar build.gradle.kts service.properties
	@echo "$(C_BLUE)Building application jar$(C_NONE)"
	@./gradlew clean test jar

node_modules/.bin/redoc-cli:
	@npm install --dev