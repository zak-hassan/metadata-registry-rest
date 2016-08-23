#!/bin/sh
VERSION="1.0.0-SNAPSHOT"
MAINTAINERS="Zak Hassan <zak.hassan@redhat.com>"
COMPONENT="test-utils"




curl -X POST -d @sampledata.json $1 --header "Content-Type:application/json"

echo "\n Done !\n";
