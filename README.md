
To build web service image from source run:

```bash

make build image

```


To run the ui using s2i then run:

```

 oc new-build openshift/wildfly-100-centos7:latest~https://github.com/zmhassan/metadata-registry-rest.git

 ```
