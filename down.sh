#!/bin/sh

# Exits immediately if a command exits with a non-zero status
set -e

# Run docker-compose down for the database
docker-compose -f db/docker-compose.yml down
