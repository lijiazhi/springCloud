没事的时候自己写的一个微服务的小demo，有兴趣的可以看一下，算是入门级的，新手看起来比较容易上手！
整个项目目前架构如下，稍后我会一个个的模块讲解：
eureka-server   注册中心
config-server   配置中心
zuul            网关
eureka-server   客户端服务（可以理解为一个微服务，实际开发中可能会存在多个）
service-feign   feign负载均衡服务

eureka-server 注册中心其实没啥好讲的，就是一些依赖和简单的配置，直接看代码就行了

config-server 配置中心需要说一下，本人提供了本地配置中心、SVN配置中心、GIT配置中心三个示例：
server:
  port: 8888
spring:
  profiles:
    active: git
  application:
    name: config-server
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
management:
  endpoints:
    web:
      exposure:
        include: bus-refresh
当前使用的是git作为配置中心，配置如下：
spring:
  cloud:
    config:
      server:
        git:
        #仓库配置中心的配置信息
        #uri表示配置中心所在仓库的位置
          uri: https://github.com/lijiazhi/springCloudConfig/
          #search-paths表示仓库下的子目录
          search-paths: respo
          #username表示你的GitHub用户名
          username:
          #password表示你的GitHub密码
          password:
          #更改本地仓库clone的配置文件信息的路径
          basedir: D:\\localGitRepo\\
      label: master
      注释写得很清楚，我就不一一解释了，因为仓库我创建的是公共库，所以不需要密码，如果你创建私人库的话，加上账号和密码就行了。
      然后是SVN作为配置中心的一些配置：
spring:
  cloud:
    config:
      server:
        svn:
          uri: http://192.168.0.6/svn/repo/config-repo
          username: username
          password: password
        default-label: trunk
  profiles:
    active: subversion
需要注意的是：
<!--使用svn配置中心需引入此包-->
        <dependency>
            <groupId>org.tmatesoft.svnkit</groupId>
            <artifactId>svnkit</artifactId>
        </dependency>
最后是使用本地作为配置中心的配置：
spring:
  cloud:
    config:
      server:
        native:
          search-locations: classpath:/properties/
上述配置的意思是，如果本地作为配置中心，配置文件的路径在resources目录下的properties文件夹下
    
