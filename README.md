# 基于阿里云SDK构建的DDNS

使用本工具可以随时检测本地的公网IP，和指定域名当前的解析IP，一旦发现不一致，就可以将域名解析到一个变化的公网IP上。本工具基于阿里云SDK，使用java开发。

本工具对于需要架设互联网服务但又苦于没有固定公网IP的童鞋使用，理论上本工具可以替代花生壳等动态域名服务。相比之前版本，最大区别是提供了页面配置阿里云SDK参数和需要解析的DDNS，并且支持多DDNS。

要求：域名托管在阿里云，阿里云开通access Key服务，相关服务器装jdk1.8及以上运行环境或者docker环境

![#ddns-aliyun](http://assets.processon.com/chart_image/63056ec463768906ff5f9160.png "概念和设计思路")


### 使用方法
#### 阿里云购买域名，并开通access Key服务 
![输入图片说明](static/images/aliyun.png)

#### 下载源码并编译
```
git clone https://gitee.com/fastjrun/ddns.git
cd ddns
sh build.sh package_server
```
output目录下生成ddns.jar
#### 部署
##### 原生部署
将ddns.jar和ddns.sh部署到服务器同一目录下，比如/opt/ddns
```
cd /opt/ddns
# 采用后台部署方式
INIT=always nohup sh ddns.sh &
```
##### 容器化部署
也可以通过docker镜像pi4k8s/ddns:3.0进行部署，这个镜像不仅可以在一般x86服务器使用，也可以在树莓派4B上直接使用
```
docker run -itd --name ddns -p 8080:8080 pi4k8s/ddns:3.0
```
#### 使用
- 配置参数 

http:{ip}:8080/config.html

![输入图片说明](static/images/config-ipv6.png)  

点击停止按钮可以停止调度任务。点击重启按钮可以以新设置的参数重新启动调度任务。

- 配置ddns记录

http:{ip}:8080/ddns.html

![输入图片说明](static/images/ddns.png)

通过本页面可添加ddns记录，添加完记录后需要等一段时间才能生效，这里支持配置多条ddns记录。

- IP变更记录

http:{ip}:8080/ip.html

![输入图片说明](static/images/ip.png)

通过本页面可查看公网IP变更记录，可删除指定记录。另外后台启动了一个默认清理任务，每天执行一次，最多保留50条最新记录。

### 关于数据迁移
对于3.0版本用户，如果是基于docker启动，需要考虑数据迁移,数据库文件在容器路径为 /root/ddns-h2.mv.db
- 从容器里cp到宿主机后，才可以删除当前容器
- 以最新版本容器启动后，再将数据cp回容器
- 重启容器后，通过h2的web控制台登录后新建ddns_ip_change_log表


3.x版本-4.0版本
- 从容器里cp到宿主机后，才可以删除当前容器
- 以最新版本容器启动后，再将数据cp回容器
- 重启容器后，通过h2的web控制台在表ddns_sys插入一条记录

`insert into `ddns_sys` VALUES('type', 'A','解析类型（ipv6=AAAA，ipv4=A）');`

### 控制台

http:{ip}:8080/h2-console  

![输入图片说明](static/images/h2.png)

  - ipv4 获取网址：
    - https://ip.3322.net
    - https://4.ipw.cn
    - http://ip.cuiyingfeng.com
  - ipv6 获取网址：
    - https://v6.ip.zxinc.org/getip
    - https://api6.ipify.org
    - https://speed.neu6.edu.cn/getIP.php
    - https://v6.ident.me
    - https://6.ipw.cn

