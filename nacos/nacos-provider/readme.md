# 官网 nacos https://nacos.io/zh-cn/docs/quick-start.html

# 依赖mysql docker 启动mysql 
#docker run --name mysql-yh-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234   -d mysql:5.7

# 访问UI http://121.199.72.238:8848/nacos/

# 内置derby 内存数据库启动
# docker run --name nacos-yh-test -e PREFER_HOST_MODE=hostname -e SPRING_DATASOURCE_PLATFORM=derby -e MODE=standalone -p 8848:8848 -d nacos/nacos-server
# 带JVM参数 deyby启动 防止启动内存小 然后启动失败
# docker run --name nacos-yh-test -e PREFER_HOST_MODE=hostname -e SPRING_DATASOURCE_PLATFORM=derby -e MODE=standalone -e JVM_XMS=256m -e JVM_XMX=256m -p 8848:8848 -d nacos/nacos-server