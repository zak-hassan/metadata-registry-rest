#!/bin/sh
# Author: Zak Hassan
nohup /usr/local/wildfly-10.0.0.Final/bin/standalone.sh -b 0.0.0.0 -Dhibernate.ogm.datastore.host=$METADATA_REGISTRY_WEB_UI_PORT_27017_TCP_ADDR  -Dhibernate.ogm.datastore.username=admin -Dhibernate.ogm.datastore.password=admin -Djboss.http.port=18080    &

echo "Wait 3 minutes while wildfly starts up"
sleep 1m
echo "Now we will deploy"
/usr/local/wildfly-10.0.0.Final/bin/add-user.sh -u wildflyuser -p admin -e
/usr/local/wildfly-10.0.0.Final/bin/jboss-cli.sh --connect -u=wildflyuser -p=admin --file=/usr/local/batchcommand.txt
/bin/bash
