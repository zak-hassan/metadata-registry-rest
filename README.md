
To build web service image from source run:

```bash

make build image

```


To run the ui using s2i then run:

```

 oc new-build openshift/wildfly-100-centos7:latest~https://github.com/zmhassan/metadata-registry-rest.git

 ```

To setup docker compose run:

```bash
cd ..
git clone https://github.com/zmhassan/metadata-registry-rest.git
git clone https://github.com/zmhassan/metadata-registry-web-ui.git
cd ./metadatapoc
docker-compose build
docker-compose up

```

UI should be running on http://localhost:8080 

Rest API is running on http://localhost:18080

