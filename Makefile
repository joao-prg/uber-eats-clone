.EXPORT_ALL_VARIABLES:

PROJECT_VERSION:=0.0.1-SNAPSHOT
PROJECT_NAME=uber-eats-clone
GIT_HASH=$(shell git rev-parse HEAD)
BRANCH_NAME=$(shell git branch --show-current)

infos:
	@echo "$(PROJECT_NAME) $(PROJECT_VERSION) $(GIT_HASH) $(BRANCH_NAME)"

test: infos
	mvn clean verify

dev: infos
	docker-compose up -d register_db
	mvn quarkus:dev -pl register

stop: infos
	docker-compose stop

clean-volumes:
	docker-compose -f docker-compose.yml down -v

show-logs:
	docker-compose logs -f
