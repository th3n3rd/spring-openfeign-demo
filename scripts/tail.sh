#!/bin/bash

set -e

NAMESPACE=${1:-"spring-openfeign-demo"}

stern -n "$NAMESPACE" ".*" --template '{{color .ContainerColor .ContainerName}} {{.Message}}{{"\n"}}'
