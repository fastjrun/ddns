#!/bin/bash

accessKeyId=$1
accessKeySecret=$2
configDomain=$3
rR=$4
cron=$5


java -jar ./ddns.jar --ddns.aliyun.accessKeyId=$accessKeyId --ddns.aliyun.accessKeySecret=$accessKeySecret \
--ddns.checkIPTask.configDomain=$configDomain  --ddns.checkIPTask.rR=$rR --ddns.checkIPTask.cron="$cron"
