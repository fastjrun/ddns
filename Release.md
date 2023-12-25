### V4.0
- 支持ipv6

### V3.2
- 引入mysql驱动

### V3.1.1
- 改进：获取指定局域网公网IP如果为空的话，则不做记录

### V3.1
- 新增动态IP变更记录列表功能
- 只保留最新的50条动态IP变更记录(每24小时执行一次)
- 支持通过h2的web控制台修改数据库

### V3.0
- 使用[sdkg](https://gitee.com/fastjrun/sdkg)重构，增加页面和数据库（h2）支撑
- 支持动态配置
  - 阿里云sdk配置
  - 检查IP和域名是否匹配时间间隔
  - ddns列表
- 支持多ddns解析
- 支持重启和停止
- 修改了 [dns缓存问题](https://gitee.com/fastjrun/ddns/issues/I4B33H)

### V2.0
- 去掉不稳定的外网IP查询工具类和不必要的依赖
- 框架升级为spring-boot2.1.3
- 去掉quartz和jsoup
- 去掉脚本和配置文件
- 更新逻辑，支持新域名RR，如果当前RR不存在，则会新增一条域名解析记录
- 封装Docker，同时支持arm和x86架构
