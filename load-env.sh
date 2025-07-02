#!/bin/bash

# Check if file .env is exists
if [ ! -f .env ]; then
  echo ".env file not found!"
  exit 1
fi

# Load environment variables from .env file
export $(grep -v '^#' .env | xargs)

echo "Environment variables loaded from .env"