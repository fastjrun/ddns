#!/bin/bash

locateIPUrl=$1
accessKeyId=$2
accessKeySecret=$3
configDomain=$4
rR=$5
cron=$6


java -jar ./ddns.jar --ddns.locateIPUrl=$locateIPUrl --ddns.aliyun.accessKeyId=$accessKeyId \
--ddns.aliyun.accessKeySecret=$accessKeySecret --ddns.checkIPTask.configDomain=$configDomain  \
--ddns.checkIPTask.rR=$rR --ddns.checkIPTask.cron="$cron"
