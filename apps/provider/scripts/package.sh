#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)"

cd "$SCRIPT_DIR/.."

APPLICATION=$(basename "$PWD")

echo "Packaging $APPLICATION as a container image"
./gradlew bootBuildImage
