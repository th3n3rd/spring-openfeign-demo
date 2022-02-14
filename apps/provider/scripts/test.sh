#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)"

cd "$SCRIPT_DIR/.."

APPLICATION=$(basename "$PWD")

echo "Running tests for $APPLICATION and publish contracts verification results"
./gradlew clean test
