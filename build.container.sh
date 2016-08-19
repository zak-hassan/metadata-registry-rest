#!/bin/sh
VERSION="1.0.0-SNAPSHOT"
AUTHOR="Zak Hassan <zak.hassan@redhat.com>"


echo   " Building ..."
docker   build  --rm -t  metadata-registry-rest  .
echo   " Done .."
