#!/bin/bash


FILE=~/ddns-h2.mv.db
if test -f "$FILE"; then
    INIT="never"
fi

java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar ddns.jar --spring.datasource.initialization-mode=${INIT}
