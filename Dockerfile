FROM openjdk:8-alpine

ADD target/ddns.jar /opt

ADD ddns.sh /opt

WORKDIR /opt

ENV LOCATEIP_URL "http://ddns.pi4k8s.cn:20080/ip"
ENV ACCESS_KEY_ID "accessKeyId"
ENV ACCESS_KEY_SECRET "accessKeySecret"
ENV CONFIG_DOMAIN "pi4k8s.cn"
ENV RR "test"
ENV CRON "0 * * * * ?"

ENTRYPOINT sh /opt/ddns.sh $LOCATEIP_URL $ACCESS_KEY_ID $ACCESS_KEY_SECRET $CONFIG_DOMAIN $RR "$CRON"