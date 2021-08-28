使用本工具可以随时检测本地的公网IP，和指定域名当前的解析IP，一旦发现不一致，就可以将域名解析到一个变化的公网IP上。本工具使用java开发，对于linux机器，可以启动定时服务(nohup ./startup.sh &)，也可以将task.sh配置到服务器的cron里；对于windows机器，直接双击startup.bat即可。

本工具对于需要架设互联网服务但又苦于没有固定公网IP的童鞋使用，理论上本工具可以替代花生壳等动态域名服务。

要求：域名托管在阿里云，阿里云开通access Key服务，相关服务器装jdk1.7及以上运行环境，配置JAVA_HOME环境变量。

![输入图片说明](http://git.oschina.net/uploads/images/2016/0911/200431_2ec8abf9_639443.png "概念和设计思路")


###使用方法
####阿里云购买域名，并开通access Key服务
![#ddns-aliyun](http://git.oschina.net/uploads/images/2016/0911/200351_e8e22925_639443.png "如何开通access Key服务")
####选择分支1.0，下载源码到本地
####导入工程到eclipse或者其它ide中
####使用该工程的测试用例AliYunUtilTest新增域名解析记录，并记录下recordId
![输入图片说明](http://git.oschina.net/uploads/images/2016/0912/072310_e6f8244f_639443.png "在这里输入图片标题")
####工程根目录下执行，生成部署包（目录格式或者tar.gz格式）
```
#构建、打包windows下环境
mvn clean package -Pnocheck,local
#构建、打包linux下环境
mvn clean package -Pnocheck,prod
```
####将部署包部署在指定在服务器，解压后调整ddns.properties文件中配置

