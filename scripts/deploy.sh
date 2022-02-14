#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)"
NAMESPACE=${1:-"spring-openfeign-demo"}
APPS="consumer provider"

echo "Navigating to the root of the project"
cd "$SCRIPT_DIR/.."

echo "Creating $NAMESPACE namespace (if does not exist)"
kubectl create ns "$NAMESPACE" || true

for APP in $APPS; do
    pushd "apps/$APP/scripts"
        ./deploy.sh "$NAMESPACE"
    popd
done

echo "Running smoke test"
RESULT=$(curl -s http://consumer.spring-openfeign-demo.k8s)
if [[ ! "$RESULT" =~ "Hello Everybody!" ]]; then
    echo "Smoke test failed"
    exit 1
fi

echo "Everything has been successfully deployed!"
