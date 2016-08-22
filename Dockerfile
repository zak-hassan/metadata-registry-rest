FROM openshift/base-centos7

MAINTAINER Zak Hassan zak.hassan@redhat.com

WORKDIR /usr/local

RUN yum install -y curl wget unzip java-1.8.0-openjdk-devel.x86_64

RUN curl -O -J -L  http://download.jboss.org/wildfly/10.0.0.Final/wildfly-10.0.0.Final.zip && unzip wildfly-10.0.0.Final.zip -d /usr/local && touch /usr/local/wildfly-10.0.0.Final/standalone/deployments/jaxrs-1.0-SNAPSHOT.war.dodeploy
RUN curl -O -J -L   https://sourceforge.net/projects/hibernate/files/hibernate-ogm/5.0.1.Final/hibernate-ogm-modules-wildfly10-5.0.1.Final.zip/download &&  mkdir -p /usr/local/hibernate_ogm_wildfly10-5.0.1 && unzip hibernate-ogm-modules-wildfly10-5.0.1.Final.zip -d /usr/local/hibernate_ogm_wildfly10-5.0.1

ENV JAVA_HOME   /usr/lib/jvm/java-1.8.0
ENV JBOSS_HOME  /usr/local/wildfly-10.0.0.Final
ENV JBOSS_MODULEPATH  $JBOSS_HOME/modules:/usr/local/hibernate_ogm_wildfly10-5.0.1

COPY  target/jaxrs-1.0-SNAPSHOT.war  /usr/local/wildfly-10.0.0.Final/standalone/deployments/jaxrs-1.0-SNAPSHOT.war
COPY  src/main/docker/entrypoint.sh    /etc/entrypoint.sh

RUN chown -R 1001:0 /usr/local/wildfly-10.0.0.Final && chown -R 1001:0 /usr/local/hibernate_ogm_wildfly10-5.0.1 && \
    chmod -R ug+rwx /usr/local/wildfly-10.0.0.Final && \
chmod -R ug+rw /usr/local/hibernate_ogm_wildfly10-5.0.1

USER 1001

EXPOSE 18080 9990 9999 4447 5455 7500 45700 45688 55200 54200 23364

ENTRYPOINT ["/etc/entrypoint.sh"]
