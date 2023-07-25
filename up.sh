#!/bin/sh

# Exits immediately if a command exits with a non-zero status
set -e


# Run docker-compose up for the database
docker-compose -f db/docker-compose.yml up -d

