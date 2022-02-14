#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)"
APPS="consumer provider"

echo "Navigating to the root of the project"
cd "$SCRIPT_DIR/.."

for APP in $APPS; do
    pushd "apps/$APP/scripts"
        ./test.sh
    popd
done

echo "Everything has been successfully tested!"
