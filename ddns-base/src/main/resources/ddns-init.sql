insert into `ddns_sys` (prop_name,prop_value,prop_desc)
VALUES ('locateIPUrl', 'http://ip.cuiyingfeng.com','公网查询服务器（参考说明）'),
       ('accessKeyId', 'xxx',''),
       ('accessKeySecret', 'yyy',''),
       ('configDomain', 'pi4k8s.cn','域名'),
       ('type', 'A','解析类型（ipv6=AAAA，ipv4=A）'),
       ('interval', '5','调度间隔（分钟）');