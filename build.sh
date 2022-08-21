#!/bin/bash

echo "build ..."
rm -rf output/*
mkdir -p output

if [ "ci" = $1 ] ; then
    mvn clean compile -pl ddns-base,ddns-bundle -am -Dbdgc.skip=false
elif [ "package_server" = $1 ] ; then
    mvn clean package -pl ddns-server -am -Dbdgc.skip=false
    cp ddns-server/target/ddns.jar ./output
    cp Dockerfile ./output
    cp ddns.sh ./output
elif [ "package_mock_server" = $1 ] ; then
    mvn clean package -pl ddns-mock-server -am -Dbdmgc.skip=false
    cp ddns-mock-server/target/ddns-mock-server.jar ./output
elif [ "clean_all" = $1 ] ; then
    mvn clean
    rm -rf output
    rm -rf ddns-bundle/src
    rm -rf ddns-bundle-mock/src
fi
echo "build done."
