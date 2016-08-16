.PHONY : build image push

build:
	mvn clean install
image:
	./build.container.sh
push:
	./docker.io.push.sh
