#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)"
NAMESPACE=${1:-"spring-openfeign-demo"}

cd "$SCRIPT_DIR/.."

APPLICATION=$(basename "$PWD")

echo "Creating $NAMESPACE namespace (if does not exist)"
kubectl create ns "$NAMESPACE" || true

echo "Deploying $APPLICATION application onto $NAMESPACE namespace"
kubectl -n "$NAMESPACE" apply -f deployment/manifest.yaml
kubectl -n "$NAMESPACE" rollout status "deploy/$APPLICATION" --timeout=120s

echo "$APPLICATION successfully deployed!"
