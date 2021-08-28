# 基于阿里云SDK构建的DDNS

使用本工具可以随时检测本地的公网IP，和指定域名当前的解析IP，一旦发现不一致，就可以将域名解析到一个变化的公网IP上。本工具基于阿里云SDK，使用java开发。

本工具对于需要架设互联网服务但又苦于没有固定公网IP的童鞋使用，理论上本工具可以替代花生壳等动态域名服务。

要求：域名托管在阿里云，阿里云开通access Key服务，相关服务器装jdk1.8及以上运行环境。

![输入图片说明](http://git.oschina.net/uploads/images/2016/0911/200431_2ec8abf9_639443.png "概念和设计思路")  


###使用方法
####阿里云购买域名，并开通access Key服务
![#ddns-aliyun](http://git.oschina.net/uploads/images/2016/0911/200351_e8e22925_639443.png "如何开通access Key服务")  

#### 下载源码并编译
```
git clone https://gitee.com/fastjrun/ddns.git
cd ddns
mvn clean package -Pnocheck
```
target目录下生成ddns.jar
#### 部署
将ddns.jar和ddns.sh部署到服务器同一目录下，比如/opt
```
cd /opt
./ddns.sh adfda dsfdsfds fastjrun.com test "0 * * * * ?"
```
#### docker部署
不想看源码的可以直接用这个docker镜像pi4k8s/ddns:2.0，这个镜像不仅可以在一般x86服务器使用，也可以在树莓派4B上直接使用
```
docker run -itd --name ddns -e ACCESS_KEY_ID=adsfdsf -e ACCESS_KEY_SECRET=adsdfd -e CONFIG_DOMAIN=fastjrun.com -e RR=test -e CRON="0 * * * * ?" pi4k8s/ddns:2.0
```


