FROM openjdk:8-alpine

ADD ddns.jar /opt
ADD ddns.sh /opt

WORKDIR /opt

ENV JAVA_OPTS "-Xms128m -Xmx128m"

ENV INIT "always"

ENTRYPOINT sh /opt/ddns.sh