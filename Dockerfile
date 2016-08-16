FROM docker.io/openshift/wildfly-100-centos7

MAINTAINER Zak Hassan zak.hassan@redhat.com

WORKDIR /opt

RUN dnf install -y curl wget unzip java-1.8.0-openjdk-devel.x86_64

#RUN curl -O -J -L  http://download.jboss.org/wildfly/10.0.0.Final/wildfly-10.0.0.Final.zip && unzip wildfly-10.0.0.Final.zip -d /opt && chown -Rv `whoami` /opt/wildfly-10.0.0.Final  &&
RUN curl -O -J -L   https://sourceforge.net/projects/hibernate/files/hibernate-ogm/5.0.1.Final/hibernate-ogm-modules-wildfly10-5.0.1.Final.zip/download &&  mkdir -p /opt/hibernate_ogm_wildfly10-5.0.1 && unzip hibernate-ogm-modules-wildfly10-5.0.1.Final.zip -d /opt/hibernate_ogm_wildfly10-5.0.1

ENV JAVA_HOME   /usr/lib/jvm/java-1.8.0
ENV JBOSS_HOME  /opt/wildfly-10.0.0.Final
ENV JBOSS_MODULEPATH  $JBOSS_HOME/modules:/opt/hibernate_ogm_wildfly10-5.0.1


COPY  target/jaxrs-1.0-SNAPSHOT.war  /opt/jaxrs-1.0-SNAPSHOT.war
COPY  src/main/docker/batchcommand.txt  /opt/batchcommand.txt
COPY  src/main/docker/entrypoint.sh    /etc/entrypoint.sh

EXPOSE 18080 9990 9999 4447 5455 7500 45700 45688 55200 54200 23364

ENTRYPOINT ["/etc/entrypoint.sh"]
