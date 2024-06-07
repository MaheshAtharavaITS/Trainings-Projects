#!/bin/bash

# Set the current working directory
PWD="C:\Flyway\flyway-commandline-10.14.0-windows-x64\flyway-10.14.0"

# PostgreSQL specific environment variables
POSTGRES_HOST="localhost"
POSTGRES_PORT="5432"
POSTGRES_DB="flyway_docker"
POSTGRES_USER="postgres"
POSTGRES_PASSWORD="root"

# Run Flyway migrations
docker run \
--env FLYWAY_URL="jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB}" \
--env FLYWAY_USER=${POSTGRES_USER} \
--env FLYWAY_PASSWORD=${POSTGRES_PASSWORD} \
--rm \
--network host \
#-p 5432:5432 \
-v ${PWD}/migration:/flyway/migrations \
-v ${PWD}/drivers:/flyway/drivers \
-v ${PWD}/conf:/flyway/conf \
redgate/flyway:9.19.1 \
-configFiles=conf/flyway.toml \
migrate
