#!/bin/sh
## Author: Zak Hassan
echo "Wait a minutes while wildfly starts up"
/usr/local/wildfly-10.0.0.Final/bin/standalone.sh -b 0.0.0.0 -Dhibernate.ogm.datastore.host=$METADATA_REGISTRY_WEB_UI_PORT_27017_TCP_ADDR  -Dhibernate.ogm.datastore.username=$MONGODB_USER -Dhibernate.ogm.datastore.password=$MONGODB_PASSWORD -Djboss.http.port=18080
