#!/bin/bash

# Pull new changes
#git pull

# Prepare War
mvn clean
mvn package

# Ensure, that docker-compose stopped
docker-compose down


# Start new deployment
docker-compose -f docker-compose-test.yml up --build -d