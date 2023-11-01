PWD=$(shell pwd -L)
MVN_CMD=./mvnw

ifneq (,$(wildcard .env))
    include .env
    export
endif

all: help

compile:                                                     ## Compile project using the maven
	@${MVN_CMD} clean package -DskipTests

build:                                                       ## Build project image using maven spring boot plugin
	@${MVN_CMD} clean spring-boot:build-image -DskipTests

test:                                                        ## Execute all tests of the project using maven
	@${MVN_CMD} clean test

test-unit:                                                   ## Execute unit tests of the project using maven
	@${MVN_CMD} clean surefire:test

test-integration:                                            ## Execute integration tests of the project using maven
	@${MVN_CMD} clean failsafe:integration-test

run:                                                         ## Execute the project using maven spring boot plugin
	@${MVN_CMD} clean spring-boot:run

help:                                                        ## Display help screen
	@echo "Usage:"
	@echo "	 make [COMMAND]"
	@echo "	 make help \n"
	@echo "Commands: \n"
	@grep -h -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
