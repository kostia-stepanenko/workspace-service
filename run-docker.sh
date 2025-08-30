#!/usr/bin/env bash

docker run -p 9090:8081 \
  -e SERVER_ADDRESS=0.0.0.0 \
  --name workspace-servicer \
  workspace-service:0.0.1-SNAPSHOT