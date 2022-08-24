# 基于阿里云SDK构建的DDNS

使用本工具可以随时检测本地的公网IP，和指定域名当前的解析IP，一旦发现不一致，就可以将域名解析到一个变化的公网IP上。本工具基于阿里云SDK，使用java开发。

本工具对于需要架设互联网服务但又苦于没有固定公网IP的童鞋使用，理论上本工具可以替代花生壳等动态域名服务。

要求：域名托管在阿里云，阿里云开通access Key服务，相关服务器装jdk1.8及以上运行环境或者docker环境

![#ddns-aliyun](http://assets.processon.com/chart_image/63056ec463768906ff5f9160.png "概念和设计思路")


### 使用方法
#### 阿里云购买域名，并开通access Key服务
![#ddns-aliyun](static/images/aliyun.png "如何开通access Key服务")

#### 下载源码并编译
```
git clone https://gitee.com/fastjrun/ddns.git
cd ddns
sh build.sh package_server
```
output目录下生成ddns.jar
#### 部署
将ddns.jar和ddns.sh部署到服务器同一目录下，比如/opt
```
cd /opt
sh ddns.sh
```
#### docker部署
不想看源码的可以直接用这个docker镜像pi4k8s/ddns:3.0，这个镜像不仅可以在一般x86服务器使用，也可以在树莓派4B上直接使用
```
docker run -itd --name ddns -p 8080:8080 pi4k8s/ddns:3.0
```
#### 使用
- 配置阿里云api参数 
http:{ip}:8080/config.html
![#ddns-config](static/images/config.png "")

- 配置ddns记录
http:{ip}:8080/ddns.html
![#ddns-ddns](static/images/ddns.png "")