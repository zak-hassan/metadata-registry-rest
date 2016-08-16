#!/bin/sh
VERSION="1.0.0-SNAPSHOT"
AUTHOR="Zak Hassan <zak.hassan@redhat.com>"



REGISTRY=$(oc get service docker-registry --template='{{index .spec.clusterIP}}:{{index .spec.ports 0 "port"}}' -n default)

docker   build  --rm -t  metadata-registry-rest  .
docker tag  metadata-registry-rest $REGISTRY/redhatanalytics/metadata-registry-rest
docker push $REGISTRY/redhatanalytics/metadata-registry-rest
