#!/bin/sh

# Exits immediately if a command exits with a non-zero status
set -e

# Run 'docker-compose up' for database deployment
docker-compose -f db/docker-compose.yml up -d

# Run Maven Clean Install
echo "Running 'mvn clean install'..."
mvn clean install

# Start Spring Boot App
echo "Starting Spring Boot app..."
mvn spring-boot:run


