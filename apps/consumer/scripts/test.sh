#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)"

cd "$SCRIPT_DIR/.."

APPLICATION=$(basename "$PWD")

echo "Running tests for $APPLICATION and generate contracts"
./gradlew clean test

echo "Publishing generated contracts for $APPLICATION"
./gradlew pactPublish
