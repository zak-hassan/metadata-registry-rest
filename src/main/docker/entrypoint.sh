#!/bin/sh
# Author: Zak Hassan
nohup /opt/wildfly-10.0.0.Final/bin/standalone.sh -b 0.0.0.0 -Dhibernate.ogm.datastore.host=$METADATA_REGISTRY_WEB_UI_PORT_27017_TCP_ADDR  -Djboss.http.port=18080    &

echo "Wait 3 minutes while wildfly starts up"
sleep 1m
echo "Now we will deploy"
/opt/wildfly-10.0.0.Final/bin/jboss-cli.sh --connect --file=/opt/batchcommand.txt
/bin/bash
