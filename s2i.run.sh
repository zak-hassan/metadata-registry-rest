 docker run -p 8080:8080 wildflymetaregistry -e "JAVA_OPTS_EXT=-Dhibernate.ogm.datastore.host=$METADATA_REGISTRY_WEB_UI_PORT_27017_TCP_ADDR"
