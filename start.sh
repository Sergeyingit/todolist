#!/bin/bash

# Pull new changes
git pull

# Prepare War
mvn clean
mvn package

# Ensure, that docker-compose stopped
docker-compose stop

export DB_USERNAME=$1
export DB_PASSWORD=$2
export DB_ROOT_PASSWORD=$3

# Start new deployment
docker-compose up --build -d