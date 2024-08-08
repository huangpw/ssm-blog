# 🔥实现项目001-2023基于SSM框架开发的个人博客项目

## 一、项目介绍

### 1、项目简介

本项目为个人博客项目，旨在对SSM的综合应用，提高大家对这三个框架的的基本使用能力，项目分为前台和后台，博客基本的功能齐全，希望大家能够认真的把该项目完成。

### 2、技术架构

- Spring
- SpringMVC
- Mybatis (通用Mapper)
- Mysql
- Jquery、Ajax、Hutool
- Maven
- jsp
- BootStrap、Editormd、Layerjs
- layui
- font-awesome

Spring + SpringMVC + Mybatis + Mysql + Jquery + Maven + jsp + BootStrap + layui + Editormd + Echarts + font-awesome

### 3、开发环境

- Tomcat 9
- Mysql 8
- IDEA2020 +

### 4、功能模块

####  4.1 后台

##### 用户模块

用户登录(多用户) 、用户个人信息修改(异步上传头像)、登录信息历史、退出系统、用户增删改查

##### 文章模块

文章添加（采用 editormd 在线实时编辑），文章列表条件分页查询、文章修改（文章是否公开修改、文章置顶）、文章删除

##### 公告模块

增删改查

##### 评论模块

增删改查 

##### 留言模块

增删改查 

##### 栏目管理模块

栏目父级和子级添加、修改和删除 

##### 系统设置

系统基本站点标题、域名和备案设置

##### 日志模块

slf4j + aop完成日志切面管理

#### **4.2 前台**

##### 用户模块

用户登录、用户注册

##### 文章模块

文章列表展示、文章搜索、置顶文章推荐、热门文章推荐、 最近访客、文章详情页(内嵌editormd)。

##### 评论模块

发表文章评论、一级评论和二级评论展示

##### 留言模块

给博主留言

##### 友链管理

博客友情链接

##### 站点公告

站点公告展示



## 二、环境搭建

### 1、数据库表设计

#### 1.1 用户表

| **列名**      | **数据类型** | **长度** | **是否主键** | **注释**                       |
| ------------- | ------------ | -------- | ------------ | ------------------------------ |
| uid           | int          | 11       | 主键         | 主键                           |
| username      | varchar      | 32       |              | 用户名                         |
| nickname      | varchar      | 32       |              | 昵称                           |
| password      | varchar      | 32       |              | 密码MD5加密                    |
| role          | char         | 1        |              | 0:管理员 1:普通用户            |
| createTime    | char         | 10       |              | 创建时间                       |
| lastLoginTime | char         | 19       |              | 登录时间                       |
| state         | char         | 1        |              | 账号是否被锁定 0:锁定 1:未锁定 |
| loginIp       | varchar      | 17       |              | 登录ip                         |
| phone         | char         | 11       |              | 手机号码                       |
| loginCout     | int          | 11       |              | 登录次数                       |
| img           | varchar      | 255      |              | 用户头像                       |

#### 1.2 文章表

| **列名**    | **数据类型** | **长度** | **是否主键** | **注释**                   |
| ----------- | ------------ | -------- | ------------ | -------------------------- |
| aid         | int          | 11       | 主键         | 主键                       |
| title       | varchar      | 255      |              | 文章标题                   |
| digest      | varchar      | 255      |              | 摘要                       |
| content     | longtext     | 255      |              | 文章内容                   |
| cid         | int          | 11       |              | 所属标签分类               |
| visit_count | int          | 10       |              | 访问量                     |
| create_time | char         | 19       |              | 发布时间                   |
| update_time | char         | 19       |              | 更新时间                   |
| is_hot      | int          | 1        |              | 是否热门 0：不热门 1：热门 |
| logo        | varchar      | 255      |              | 文章logo                   |
| uid         | int          | 11       |              | 发布者                     |
| isOpen      | char         | 1        |              | 是否公开 0:不公开 1:公开   |
| thumbsUp    | bigint       | 10       |              | 点赞数                     |
| tagNames    | varchar      | 255      |              | 文章标签                   |
| isCommented | char         | 1        |              | 是否被评论                 |

####  1.3 栏目表

| **列名** | **数据类型** | **长度** | **是否主键** | **注释** |
| -------- | ------------ | -------- | ------------ | -------- |
| cid      | int          | 11       | 主键         | 主键     |
| cname    | varchar      | 255      |              | 栏目名称 |

#### 1.4 链接表

| **列名**  | **数据类型** | **长度** | **是否主键** | **注释**             |
| --------- | ------------ | -------- | ------------ | -------------------- |
| id        | int          | 11       | 主键         | 主键                 |
| site_name | varchar      | 255      |              | 站点名               |
| site_url  | varchar      | 255      |              | 站点地址             |
| site_desc | varchar      | 255      |              | 站点描述  简单备注下 |
| sort      | int          | 1        |              | 排序                 |

#### 1.5 标签表

| **列名** | **数据类型** | **长度** | **是否主键** | **注释** |
| -------- | ------------ | -------- | ------------ | -------- |
| tid      | int          | 11       | 主键         | 主键     |
| tname    | varchar      | 32       |              | 标签名称 |
| cid      | varchar      | 11       |              | 所属栏目 |

### 2、新建Maven工程

![图片1](/images/buildpro101/图片1.png)

![图片2](/images/buildpro101/图片2.png)

创建Maven工程完毕后，设置好Maven基本目录，并指定源码和配置文件路径

![图片3](/images/buildpro101/图片3.png)

创建项目的基本工程包结构，如下

![图片4](/images/buildpro101/图片4.png)

### 3、配置文件

#### 3.1 Spring

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	   xmlns:aop="http://www.springframework.org/schema/aop" xmlns:context="http://www.springframework.org/schema/context"
	   xmlns:tx="http://www.springframework.org/schema/tx"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans
	 http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/aop
	http://www.springframework.org/schema/aop/spring-aop.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd
	http://www.springframework.org/schema/tx
	 http://www.springframework.org/schema/tx/spring-tx.xsd">

	<!--
		批量扫描包
		让SpringIOC容器管理对象
		编写实体类的时候别忘记给实体类加上@Component
	-->
	<context:component-scan
			base-package="com.bjpowernode.blog" />

	<!--引入spring-service.xml-->
	<import resource="classpath:spring/spring-service.xml" />

</beans>
```

spring-dao.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	   xmlns:aop="http://www.springframework.org/schema/aop" xmlns:context="http://www.springframework.org/schema/context"
	   xmlns:tx="http://www.springframework.org/schema/tx"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans
	 http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/aop
	http://www.springframework.org/schema/aop/spring-aop.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd
	http://www.springframework.org/schema/tx
	 http://www.springframework.org/schema/tx/spring-tx.xsd">

	<!--引入jdbc.properties,获取链接数据库的基本信息-->
	<context:property-placeholder location="classpath:mybatis/jdbc.properties" />


	<!--创建数据库连接池:dbcp连接池-->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName" value="${jdbc.driverClass}" />
		<property name="url" value="${jdbc.jdbcUrl}" />
		<property name="username" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
	</bean>

	<!--
		生成SqlSessionFactory
		需要配置原先SqlMapConfig.xml中的功能
	-->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
			<!--引入数据源-->
			<property name="dataSource" ref="dataSource" />
			<!--给包指定别名-->
			<property name="typeAliasesPackage" value= "com.bjpowernode.blog.back.bean" />
			<!--导入映射文件-->
		    <property name="mapperLocations" value="classpath:mappers/*Mapper.xml" />
        <!--导入SqlMapConfig.xml:就是为了打印sql-->
        <property name="configLocation" value="classpath:mybatis/SqlMapConfig.xml" />

			<!--配置Mybatis分页插件:PageHelper-->
			<property name="plugins">
				<array>
					<bean class="com.github.pagehelper.PageHelper">
						<property name="properties">
							<props>
								<!--dialect:数据库方言-->
								<prop key="dialect">mysql</prop>
								<prop key="supportMethodsArguments">true</prop>
							</props>
						</property>
					</bean>
				</array>
			</property>
	</bean>

	<bean class="tk.mybatis.spring.mapper.MapperScannerConfigurer">
		<!--查找SqlsessionFactory-->
		<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
		<!--扫描mapper接口包-->
		<property name="basePackage"
                  value="com.bjpowernode.blog.back.mapper,
com.bjpowernode.blog.fore.mapper" />
	</bean>

</beans>
```

spring-service.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	   xmlns:aop="http://www.springframework.org/schema/aop" xmlns:context="http://www.springframework.org/schema/context"
	   xmlns:tx="http://www.springframework.org/schema/tx"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans
	 http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/aop
	http://www.springframework.org/schema/aop/spring-aop.xsd
	http://www.springframework.org/schema/tx
	 http://www.springframework.org/schema/tx/spring-tx.xsd">

	<import resource="classpath:spring/spring-dao.xml" />

	<!--事务管理器-->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<!--引入数据源-->
		<property name="dataSource" ref="dataSource" />
	</bean>

	<aop:config>
		<aop:pointcut
				expression="execution(* com.bjpowernode.blog.back.service.*.*(..))"
				id="trPointcut" />
		<aop:advisor pointcut-ref="trPointcut" advice-ref="trAdvice" />
	</aop:config>

	<tx:advice id="trAdvice" transaction-manager="transactionManager">
		<tx:attributes>
			<tx:method name="insert*" propagation="REQUIRED" />
			<tx:method name="delete*" propagation="REQUIRED" />
			<tx:method name="update*" propagation="REQUIRED" />
			<tx:method name="add*" propagation="REQUIRED" />
			<tx:method name="save*" propagation="REQUIRED" />
			<tx:method name="call*" propagation="REQUIRED" />
		</tx:attributes>
	</tx:advice>

</beans>
```

#### 3.2 SpringMVC

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	   xmlns:context="http://www.springframework.org/schema/context"
	   xmlns:mvc="http://www.springframework.org/schema/mvc"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
	http://www.springframework.org/schema/mvc
	http://www.springframework.org/schema/mvc/spring-mvc.xsd">

	<!--扫描Controller组件对象-->
	<context:component-scan
			base-package="com.bjpowernode.blog.back.controller,
com.bjpowernode.blog.base.controller,com.bjpowernode.blog.fore.controller" />

	<!--处理器适配器和处理器映射器-->
	<mvc:annotation-driven>
		<mvc:message-converters register-defaults="true">
			<!--json解析器-->
			<bean id="mappingJacksonHttpMessageConverter"
				  class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
				<!--class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter">-->
				<property name="supportedMediaTypes">
					<list>
						<value>text/html;charset=UTF-8</value>
						<value>application/json</value>
						<value>application/xml;charset=UTF-8</value>
					</list>
				</property>
			</bean>
		</mvc:message-converters>
	</mvc:annotation-driven>

	<!--处理静态资源文件-->
	<mvc:default-servlet-handler />

	<!--视图解析器-->
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/article/"/>
		<property name="suffix" value=".jsp"/>
	</bean>

    <!--文件上传解析器-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8"/>
    </bean>

    <!--注册:登录拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
            <!--path拦截的路径，不是目录，根路径拦截失效[/项目名不需要写]-->
            <mvc:mapping path="/**"/>
            <mvc:exclude-mapping path="/js/**" />
            <mvc:exclude-mapping path="/article/saveComments" />
            <mvc:exclude-mapping path="/login/**" />
            <mvc:exclude-mapping path="/**" />
            <mvc:exclude-mapping path="/code/**" />
            <mvc:exclude-mapping path="/js/**" />
            <mvc:exclude-mapping path="/bootstrap-3.3.7-dist/**" />
            <mvc:exclude-mapping path="/css/**" />
            <mvc:exclude-mapping path="/font-awesome-4.7.0/**" />
            <mvc:exclude-mapping path="/images/**" />
            <mvc:exclude-mapping path="/kindeditor/**" />
            <mvc:exclude-mapping path="/layui/**" />
            <mvc:exclude-mapping path="/upload/**" />
            <bean class="com.bjpowernode.blog.base.interceptor.LoginInterceptor" />
        </mvc:interceptor>
    </mvc:interceptors>
</beans>
```

#### 3.3 Mybatis

jdbc.properties

```properties
jdbc.username = root
jdbc.password = root
jdbc.jdbcUrl = jdbc\:mysql\:///blog
jdbc.driverClass = com.mysql.jdbc.Driver
```

SqlMapConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--打印sql-->
    <settings>
        <!-- 打印查询语句 -->
        <setting name="logImpl" value="STDOUT_LOGGING" />
    </settings>
</configuration>
```

#### 3.4 POM文件

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.bjpowernode</groupId>
  <artifactId>blog</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>


    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
        <!-- log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>

        <!--hutool-->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.5.7</version>
        </dependency>

        <!--Spring-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.0.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.0.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>5.0.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>5.0.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.0.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.6.8</version>
        </dependency>

        <!--Mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>
        <!--tkMapper-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper</artifactId>
            <version>4.1.5</version>
        </dependency>
        <!--Mybatis和Spring整合-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.3</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.36</version>
            <scope>runtime</scope>
        </dependency>

        <!-- 数据库连接池 -->
        <dependency>
            <groupId>commons-dbcp</groupId>
            <artifactId>commons-dbcp</artifactId>
            <version>1.4</version>
        </dependency>

        <!--引入fastjson-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.3</version>
        </dependency>

        <!--jstl-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.0</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>

        <!-- pageHelper：用于分页 -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>4.1.6</version>
        </dependency>

        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.12</version>
            <scope>provided</scope>
        </dependency>

        <!--上传文件-->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.3.1</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- tomcat插件 -->
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <port>8080</port>
                    <path>/blog</path>
                    <uriEncoding>UTF-8</uriEncoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

#### 3.5 Web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

     <!--读取Spring配置文件:applicationContext.xml:生成IOC-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring/applicationContext.xml</param-value>
    </context-param>
    
    <!--配置监听器监听IOC容器是否正常工作-->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    
    <!--SpringMVC的分发器:DispatcherServlet-->
    <servlet>
        <servlet-name>springMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

        <!--因为服务器启动的时候会第一时间读取web.xml文件，在服务器启动的
        的时候加载springMVC.xml ServletConfig-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springMVC/springMVC.xml</param-value>
        </init-param>
        <!--在服务器启动的时候就初始化该Servlet对象-->
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springMVC</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    
    <!--编码过滤器-->
    <filter>
        <filter-name>EncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <!--强制使用该编码-->
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>EncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```



## 三、用户模块

### 1、用户登录

#### 1.1 准备工作

找到资料下后台登录的静态资源文件，导入到项目中，并将其修改成jsp文件

![图片5](/images/buildpro101/图片5.png)

注意：

​		登录界面我们可以放置在webapp目录，用户可以直接访问，其他后台的页面需要放到WEB-INF目录，用户不能直接访问，需要通过后台跳转才行。

![图片6](/images/buildpro101/图片6.png)

#### 1.2 处理前端文件

把css、js和图片拷贝到webapp目录下，如上图，在index.jsp引入如下样式和js

```jsp
<link rel="icon" href="/blog/images/favicon.ico" type="image/x-icon"/>
<link href="/blog/css/back/default.css" rel="stylesheet" type="text/css" />

<!--必要样式-->
<link href="/blog/css/back/styles.css" rel="stylesheet" type="text/css" />
<link href="/blog/css/back/demo.css" rel="stylesheet" type="text/css" />
<link href="/blog/css/back/loaders.css" rel="stylesheet" type="text/css" />
<script src="/blog/js/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/blog/js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src='/blog/js/jquery/stopExecutionOnTimeout.js?t=1'></script>
<script src="/blog/js/layer-3.5.1/layer.js" type="text/javascript"></script>
<script src="/blog/js/jquery/Particleground.js" type="text/javascript"></script>
<script src="/blog/js/jquery/jquery.mockjax.js" type="text/javascript"></script>
<script src="/blog/js/myJs/controlLogin.js" type="text/javascript"></script>

```

#### 1.3 创建后台模块的包结构

![图片7](/images/buildpro101/图片7.png)

#### 1.4 代码编写

##### 1.4.1 LoginController

在controller包下创建跳转到后台登录页面的 LoginController（也可以直接通过浏览器访问，因为放置在webapp目录）

![图片8](/images/buildpro101/图片8.png)

```java
@Controller
public class LoginController {

    //跳转到后台登录页面
    @RequestMapping("/login")
    public String toLogin(){
        return "redirect:/login.jsp";
    }
}
```

![图片9](/images/buildpro101/图片9.png)

##### 1.4.2 登录页面图片验证码

首先，我们找到登录页面的登录 Html 代码

```html
<div class='login_fields__password'>
    <div class='icon'>
        <img alt="" src='/blog/images/key.png'>
    </div>
    <input name="code" placeholder='验证码' maxlength="4"  class="ValidateNum" type='text' 
           name="ValidateNum" autocomplete="off">
    <div class='validation' style="opacity: 1; right: -5px;top: -3px;">
        <canvas style="position: relative;top: 5px;cursor: pointer" class="J_codeimg" 
                id="myCanvas" onclick="Code();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!
        </canvas>
    </div>
</div>
```

这个图片验证的生成使用的是html5的canvas的绘图技术实现的，这个我们不用关心，我们只需要关注单机验证码刷新验证码的方法

![图片10](/images/buildpro101/图片10.png)

这个js的位置如下图

![图片11](/images/buildpro101/图片11.png)

canvas绘制图片验证码，这个代码同学们可以不用关心，知道有这回事就行了

```js
function showCheck(a) {
    CodeVal = a;
    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");
    ctx.clearRect(0, 0, 1000, 1000);
    ctx.font = "80px 'Hiragino Sans GB'";
    ctx.fillStyle = "#E8DFE8";
    ctx.fillText(a, 0, 100);
}
```

定义发送异步请求验证码函数

```js
function Code() {
    //发送异步请求验证码
    $.ajax({
        url : '/blog/code',
        type : 'get',
        dataType : 'json',
        success : function (data) {
            var AdminCode = data;
            showCheck(AdminCode);
        }
    })}
```

编写后台接收图片验证请求的Controller

![图片12](/images/buildpro101/图片12.png)

hutool提供了生成验证码的工具类

```java
package com.bjpowernode.blog.base.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ValidateCodeServlet {

    @GetMapping("/code")
    public String code(HttpSession session){
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(140, 35, 4, 4);

        //把正确的验证码保存在session中
        String code = captcha.getCode();
        session.setAttribute("code",code);
        //返回验证码字符串
        return code;
    }
}

```

layui 通过 use 方法加载模块。当你的 JS 需要用到 layui 模块的时候，且避免到处写 layui.use() 的麻烦，我们使用use加载用户登录校验

```js
layui.use('layer', function () {
    //非空验证
    $('input[type="button"]').click(function () {
        var login = $('.username').val();
        var pwd = $('.passwordNumder').val();
        var code = $('.ValidateNum').val();
        if (login == '') {
            ErroAlert('请输入您的账号');
            return false;
        } else if (pwd == '') {
            ErroAlert('请输入密码');
            return false;
        } else if (code == '' || code.length != 4) {
            ErroAlert('输入验证码');
            return false;
        } else {
            $('.login').addClass('test'); //倾斜特效
            setTimeout(function () {
                $('.login').addClass('testtwo'); //平移特效
            }, 300);
            setTimeout(function () {
                $('.authent').show().animate({ right: -320 }, {
                    easing: 'easeOutQuint',
                    duration: 600,
                    queue: false
                });
                $('.authent').animate({ opacity: 1 }, {
                    duration: 200,
                    queue: false
                }).addClass('visible');
            }, 500);

            //发送异步校验
            $.ajax({
                url : '/blog/user/login',
                type : 'get',
                data : {
                    'username' : $('.username').val(),
                    'password' : $('.passwordNumder').val(),
                    'code' : $('.ValidateNum').val()
                },
                dataType : 'json',
                success : function (data) {
                    setTimeout(function () {
                        $('.authent').show().animate({ right: 90 }, {
                            easing: 'easeOutQuint',
                            duration: 600,
                            queue: false
                        });
                        $('.authent').animate({ opacity: 0 }, {
                            duration: 200,
                            queue: false
                        }).addClass('visible');
                        $('.login').removeClass('testtwo'); //平移特效
                        $('.authent').hide();
                    }, 2000);
                    if(!data.ok){
                        setTimeout(function () {
                            $('.login').removeClass('test');
                            ErroAlert(data.mess);
                            //登录失败，再次发送验证码
                            $.ajax({
                                url : '/blog/code',
                                type : 'get',
                                dataType : 'json',
                                success : function (data) {
                                    var AdminCode = data;
                                    showCheck(AdminCode);
                                }
                            })
                        }, 2400);
                    }else{
                        setTimeout(function () {
                            $('.login').fadeOut(100)
                            //登录校验成功，跳转到后台首页
                            window.location.href="/blog/toIndex";
                        }, 2400);

                    }
                }
            });
        }
        return false;
    })
})
```

ErroAlert 弹出框函数定义在如下图位置

```js
function ErroAlert(e) {
    var index = layer.alert(e, { icon: 5, time: 2000, offset: 't', closeBtn: 0, title: '错误信息', btn: [], anim: 2, shade: 0 });
    layer.style(index, {
        color: '#777'
    }); 
}
```

##### 1.4.3 登录功能后台代码

因为系统中会有很多的异步请求，而这些请求可能成功，也可能失败，需要给用户一个消息或者结果集反馈，所以我们使用自定义异常用于给客户端返回操作失败消息，自定义实体类返回普通消息和成功消息，使用枚举定义异常的类型。

自定义异常类型枚举类

```java
public enum  BlogExceptionEnum{

    LOGIN_USER_PASS("001-001"),//用户名或密码错误
    LOGIN_STATE("001-002"),//账户被锁定
    LOGIN_CODE("001-003"),//验证码输入错误
    USER_UPDATE("001-004"),//用户信息更新失败

    ARTICLE_ADD("002-001"),//添加文章失败
    ARTICLE_ISOPEN("002-002"),//修改状态失败
    ARTICLE_UPDATE("002-003"),//修改文章失败
    ARTICLE_DELETE("002-004"),//删除文章失败
    ARTICLE_THUMBSUP("002-005");//更新点赞数失败

    private String type;

    BlogExceptionEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
```

异常类

```java
@Data
@ToString
public class BlogException extends RuntimeException {

    private BlogExceptionEnum blogExceptionEnum;

    public BlogException(BlogExceptionEnum blogExceptionEnum){
        super();
        this.blogExceptionEnum = blogExceptionEnum;
    }
}
```

返回消息和结果集的自定义实体类

```java
@Data
@ToString
public class ResultVo<T> {

    private boolean isOk;//是否成功
    private String mess;//返回给客户端的具体消息
    private List<T> data;//给客户端返回的数据
    private T t;//返回单个对象
}
```

用户实体类

```java
@Data
@ToString
@Table(name = "t_user")
@NameStyle(Style.normal)
public class User {

    @Id
    private String uid;
    private String username;
    private String nickname;
    private String password;
    private String role;
    private String createTime;
    private String lastLoginTime;
    private String state;
    private String loginIp;
    private String phone;
    private String loginCout;
    private String img;
}
```

用户登录 UserController 登录方法

```java
/**
* 登录功能
*/
@RequestMapping("/user/login")
@ResponseBody
public ResultVo login(User user, String code, HttpSession session, HttpServletRequest request){
    ResultVo resultVo = new ResultVo();
    try {
        //获取正确的验证码
        String correctCode = (String) session.getAttribute("code");
        //登录IP
        user.setLoginIp(request.getRemoteAddr());
        user = userService.login(user,code,correctCode);
        //把用户信息保存在session中
        session.setAttribute(BlogConstants.LOGIN_USER,user);
        resultVo.setOk(true);
        resultVo.setMess("登录成功");
    } catch (BlogException e) {
        resultVo.setOk(false);
        if(e.getBlogExceptionEnum().getType().equals("001-001")){
            //用户名或密码错误
            resultVo.setMess("用户名或密码错误");
        }else if(e.getBlogExceptionEnum().getType().equals("001-002")){
            //账号被锁定
            resultVo.setMess("账号被锁定，请联系管理员");
        }else if(e.getBlogExceptionEnum().getType().equals("001-003")){
            //账号被锁定
            resultVo.setMess("验证码错误");
        }
    }
    return resultVo;
}
```

用户登录service

```java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //用户后台登录功能
    @Override
    public User login(User user,String code,String correctCode) {
        String IP = user.getLoginIp();
        user.setLoginIp(null);
        //加密密码
        String password = SecureUtil.md5(user.getPassword());
        user.setPassword(password);

        List<User> users = userMapper.select(user);
        //先判断输入的验证码正确与否
        if(!correctCode.equals(code)){
            throw new BlogException(BlogExceptionEnum.LOGIN_CODE);
        }else{
            if(users.size() == 0){
                throw new BlogException(BlogExceptionEnum.LOGIN_USER_PASS);
            }else{
                //用户账号是否被锁定
                user = users.get(0);
                if("0".equals(user.getState())){
                    throw new BlogException(BlogExceptionEnum.LOGIN_STATE);
                }else{
                    //更新用户登录时间和登录次数和登录IP
                    user.setLoginCout(Integer.parseInt(user.getLoginCout()) + 1 + "");
                    user.setLastLoginTime(DateTimeUtil.getSysTime());
                    user.setLoginIp(IP);
                    userMapper.updateByPrimaryKeySelective(user);
                }
            }
        }
        return user;
    }
}
```

用户登录持久层

```java
public interface UserMapper extends Mapper<User> {
}
```

### 2、用户登出

#### 2.1 我们给退出按钮，添加登出事件

![图片13](/images/buildpro101/图片13.png)

代码:

```jsp
<a href="javascript:void(0)" onclick="loginOut()">退出登录</a>

//退出系统
function loginOut() {
    layer.alert('确定退出系统吗？', {
        time: 0 //不自动关闭
        ,btn: ['确定', '取消']
        ,yes: function(index){
            layer.close(index);
            location.href = "/blog/user/loginOut";
        }
    });
}
```

#### 2.2 后台Controller方法编写

```java
//登出功能
@RequestMapping("/user/loginOut")
public String loginOut(HttpSession session){
    //清除session中的用户
    session.removeAttribute("user");
    //重定向到登录页面
    return "redirect:/login.jsp";
}
```

### 3、修改用户个人信息

#### 3.1 异步查询用户输入的原始密码是否正确

点击修改个人信息下拉菜单，弹出模态窗口

![图片14](/images/buildpro101/图片14.png)

![图片15](/images/buildpro101/图片15.png)

前台js

```js
//异步校验旧密码输入是否正确
$('#oldPwd').blur(function () {
    $.post("/blog/user/verifyOldPwd",{'oldPwd':$(this).val()},function (data) {
        if(!data.ok){
            layer.msg(data.mess, {offset:'t'});
        }
    },'json');
});
```

后台Controller

```java
//异步校验用户输入的原始密码是否正确
@RequestMapping("/user/verifyOldPwd")
@ResponseBody
public ResultVo verifyOldPwd(String oldPwd,HttpSession session){
    ResultVo resultVo = new ResultVo();
    try{
        //获取当前登录用户
        User user = (User) session.getAttribute("user");
        userService.verifyOldPwd(oldPwd,user);
        resultVo.setOk(true);
    }catch (BlogException e){
        resultVo.setMess(e.getMessage());
    }
    return resultVo;
}
```

后台Service

```java
public void verifyOldPwd(String oldPwd, User user) {
    oldPwd = MD5Util.getMD5(oldPwd);
    String password = user.getPassword();
    if(!password.equals(oldPwd)){
        throw new BlogException(BlogEnum.USER_VERIFY_PASS);
    }
}
```

#### 3.2 更新用户信息

在此，前台的js非空校验、新密码和确认密码是否一致代码省略

前台js

```js
//异步更新用户信息
function updateUser() {
    //表单序列化 能够把表单的内容拼接成 key=值&key=值...,返回值是字符串
    var form = $('#updateUserForm').serialize();
    $.post("/blog/user/updateUser",form,function (data) {
        if(data.ok){
            layer.alert(data.mess, {icon: 6});

            setTimeout(function () {
                //重新登录,跳转到登录页面
                //setTimeout:隔多长时间执行指定代码
                location.href = "/blog/login.jsp";
            },1000);

        }
    },'json');
}
```

后台Controller

```java
//异步修改用户信息
@RequestMapping("/user/updateUser")
@ResponseBody
public ResultVo updateUser(User user){
    ResultVo resultVo = new ResultVo();
    try{
        userService.updateUser(user);
        resultVo.setOk(true);
        resultVo.setMess("修改用户信息成功");
    }catch (BlogException e){
        resultVo.setMess(e.getMessage());
    }
    return resultVo;
}
```

Service层

```java
@Override
public void updateUser(User user) {
    //给用户输入的新密码加密
    user.setPassword(MD5Util.getMD5(user.getPassword()));

    //count:影响记录数
    int count = userMapper.updateByPrimaryKeySelective(user);
    if(count == 0){
        throw new BlogException(BlogEnum.USER_UPDATE);
    }
}
```

## 四、文章模块

#### 1、统一跳转视图Controller

因为项目中有很多关于点击菜单跳转视图的请求，正常情况下，我们每个请求我们需要编写一个方法跳转到指定视图，所以为了简化操作，我们使用SpringMVC的 RestFul 风格来处理视图的统一跳转，代码如下

```java
@Controller
public class ViewController {

    //localhost:8080/blog/add?name=..&title=...
    //localhost:8080/blog/add/zhangsan/旅游  workbench/article/index
    @RequestMapping("/toView/{firstView}/{secondView}/{thirdView}")
    public String toView(
            @PathVariable("firstView") String firstView,
            @PathVariable("secondView") String secondView ,
            @PathVariable("thirdView") String thirdView){

        //File.separator:\
        return firstView + File.separator + secondView + File.separator + thirdView;
    }
}
```

#### 2、文章列表展示

现在我们实现文章数据的分页，本项目中基本都使用异步请求的方式，所以点击文章菜单跳转到文章首页后发送异步请求，查询文章列表数据

前台js

```js
$.post("/blog/article/list",{
    'page' : page,
    'pageSize' : pageSize,
    'title' : $('#title').val()
},function (data) {
    //设置当前文章数量
    $('#count').text(data.total + "篇文章");
    //清空原来的内容
    $('#articleBody').html("");
    //List<Article>
    var articles = data.list;
    for(var i =0 ; i < articles.length; i++){
        var article = articles[i];
        if(article.isOpen == "0"){
            $('#articleBody').append("<tr>\n" +
                "                <td><input type=\"checkbox\" class=\"input-control\" name=\"checkbox\" value=\"\" /></td>\n" +
                "                <td class=\"article-title\">"+article.title+"</td>\n" +
                "                <td>"+article.cid+"</td>\n" +
                "                <td class=\"hidden-sm\">"+article.tagNames+"</td>\n" +
                "                <td class=\"hidden-sm\">"+article.thumbsUp+"</td>\n" +
                "                <td>"+article.create_time+"</td>\n" +
                "                <td>\n" +
                "                    <a href=\"update-article.html\">修改</a> <a rel=\"6\">删除</a>&nbsp;\n" +
                "                    <input type=\"radio\" onclick=change(\'"+article.aid+"\',$(this).val()) name="+article.aid+" value=\"1\"/>公开 <input onclick=change(\'"+article.aid+"\',$(this).val()) type=\"radio\" checked name="+article.aid+" value=\"0\" />私密\n" +
                "                </td>\n" +
                "              </tr>");
        }else{
            $('#articleBody').append("<tr>\n" +
                "                <td><input type=\"checkbox\" class=\"input-control\" name=\"checkbox\" value=\"\" /></td>\n" +
                "                <td class=\"article-title\">"+article.title+"</td>\n" +
                "                <td>"+article.cid+"</td>\n" +
                "                <td class=\"hidden-sm\">"+article.tagNames+"</td>\n" +
                "                <td class=\"hidden-sm\">"+article.thumbsUp+"</td>\n" +
                "                <td>"+article.create_time+"</td>\n" +
                "                <td>\n" +
                "                    <a href=\"update-article.html\">修改</a> <a rel=\"6\">删除</a>&nbsp;\n" +
                "                    <input onclick=change(\'"+article.aid+"\',$(this).val()) type=\"radio\" name="+article.aid+" value=\"1\" checked/>公开 <input onclick= change(\'"+article.aid+"\',$(this).val()) type=\"radio\" name="+article.aid+" value=\"0\" />私密\n" +
                "                </td>\n" +
                "              </tr>");
        }
    }
    });
},'json');
```

后台Controller

```java
@RequestMapping("/article/list")
@ResponseBody
public List<Article> list(int page,int pageSize){
    //参数1:当前页码 参数2:每页记录数 pageSize 该方法等同于 limit a,b
    PageHelper.startPage(page,pageSize);
    List<Article> articles = articleService.list();
    PageInfo<Article> pageInfo = new PageInfo<>(articles);
    return pageInfo.getList();
}
```

Service代码

```java
@Override
public List<Article> list(String title) {
    List<Article> articles = articleMapper.selectAll();
    //遍历所有文章
    for (Article article : articles) {
        String cid = article.getCid();
        //根据cid查询栏目表，查询栏目对象
        Category category = categoryMapper.selectByPrimaryKey(cid);
        article.setCid(category.getCname());
    }
    return articles;
}
```

#### 3、文章列表分页

此处的分页，我们使用BootStrap的分页插件

![图片16](/images/buildpro101/图片16.png)

在项目中引入分页插件

![图片17](/images/buildpro101/图片17.png)

分页插件的模板代码

```js
//bootstrap的分页插件
$("#activityPage").bs_pagination({
    currentPage: data.pageNum, // 页码
    rowsPerPage: data.pageSize, // 每页显示的记录条数
    maxRowsPerPage: 20, // 每页最多显示的记录条数
    totalPages: data.pages, // 总页数
    totalRows: data.total, // 总记录条数
    visiblePageLinks: 3, // 显示几个卡片
    showGoToPage: true,
    showRowsPerPage: true,
    showRowsInfo: true,
    showRowsDefaultInfo: true,
    //回调函数，用户每次点击分页插件进行翻页的时候就会触发该函数
    onChangePage : function(event, obj){
        //currentPage:当前页码 rowsPerPage:每页记录数
        refresh(obj.currentPage,obj.rowsPerPage);
    }
});
```

每次操作分页插件组建的时候都会出发其中的onCHangePage函数，内置参数obj中包含了当前页码和每页记录数，所以我们可以把前端列表查询分装成一个刷新页面的函数

前端js代码如下

```js
//调用刷新方法
refresh(1,2);

//定义一个函数，发送请求不同页码对应的数据
function refresh(page,pageSize){
    //发送查询所有文章列表的异步请求
    $.post("/blog/article/list",{
        'page' : page,
        'pageSize' : pageSize,
    },function (data) {
        //设置当前文章数量
        $('#count').text(data.total + "篇文章");
        //清空原来的内容
        $('#articleBody').html("");
        //List<Article>
        var articles = data.list;
        for(var i =0 ; i < articles.length; i++){
            var article = articles[i];
            if(article.isOpen == "0"){
                $('#articleBody').append("<tr>\n" +
                    "                <td><input type=\"checkbox\" class=\"input-control\" name=\"checkbox\" value=\"\" /></td>\n" +
                    "                <td class=\"article-title\">"+article.title+"</td>\n" +
                    "                <td>"+article.cid+"</td>\n" +
                    "                <td class=\"hidden-sm\">"+article.tagNames+"</td>\n" +
                    "                <td class=\"hidden-sm\">"+article.thumbsUp+"</td>\n" +
                    "                <td>"+article.create_time+"</td>\n" +
                    "                <td>\n" +
                    "                    <a href=\"update-article.html\">修改</a> <a rel=\"6\">删除</a>&nbsp;\n" +
                    "                    <input type=\"radio\" onclick=change(\'"+article.aid+"\',$(this).val()) name="+article.aid+" value=\"1\"/>公开 <input onclick=change(\'"+article.aid+"\',$(this).val()) type=\"radio\" checked name="+article.aid+" value=\"0\" />私密\n" +
                    "                </td>\n" +
                    "              </tr>");
            }else{
                $('#articleBody').append("<tr>\n" +
                    "                <td><input type=\"checkbox\" class=\"input-control\" name=\"checkbox\" value=\"\" /></td>\n" +
                    "                <td class=\"article-title\">"+article.title+"</td>\n" +
                    "                <td>"+article.cid+"</td>\n" +
                    "                <td class=\"hidden-sm\">"+article.tagNames+"</td>\n" +
                    "                <td class=\"hidden-sm\">"+article.thumbsUp+"</td>\n" +
                    "                <td>"+article.create_time+"</td>\n" +
                    "                <td>\n" +
                    "                    <a href=\"update-article.html\">修改</a> <a rel=\"6\">删除</a>&nbsp;\n" +
                    "                    <input onclick=change(\'"+article.aid+"\',$(this).val()) type=\"radio\" name="+article.aid+" value=\"1\" checked/>公开 <input onclick= change(\'"+article.aid+"\',$(this).val()) type=\"radio\" name="+article.aid+" value=\"0\" />私密\n" +
                    "                </td>\n" +
                    "              </tr>");
            }
        }

        //bootstrap的分页插件
        $("#activityPage").bs_pagination({
            currentPage: data.pageNum, // 页码
            rowsPerPage: data.pageSize, // 每页显示的记录条数
            maxRowsPerPage: 20, // 每页最多显示的记录条数
            totalPages: data.pages, // 总页数
            totalRows: data.total, // 总记录条数
            visiblePageLinks: 3, // 显示几个卡片
            showGoToPage: true,
            showRowsPerPage: true,
            showRowsInfo: true,
            showRowsDefaultInfo: true,
            //回调函数，用户每次点击分页插件进行翻页的时候就会触发该函数
            onChangePage : function(event, obj){
                //currentPage:当前页码 rowsPerPage:每页记录数
                refresh(obj.currentPage,obj.rowsPerPage);
            }
        });
    },'json');
}
```

后台Controller修改为

```java
@RequestMapping("/article/list")
@ResponseBody
public PageInfo<Article> list(int page,int pageSize){
    //参数1:当前页码 参数2:每页记录数 pageSize 该方法等同于 limit a,b
    PageHelper.startPage(page,pageSize);
    List<Article> articles = articleService.list();
    PageInfo<Article> pageInfo = new PageInfo<>(articles);
    return pageInfo;
}
```

#### 4、文章的模糊查询

文章模块支持按文章标题模糊查询，我们只需要在refresh方法中添加指定参数即可

![图片18](/images/buildpro101/图片18.png)

后台Controller代码改为

```java
@RequestMapping("/article/list")
@ResponseBody
public PageInfo<Article> list(int page,int pageSize,String title){
    //参数1:当前页码 参数2:每页记录数 pageSize 该方法等同于 limit a,b
    PageHelper.startPage(page,pageSize);
    List<Article> articles = articleService.list(title);
    PageInfo<Article> pageInfo = new PageInfo<>(articles);
    return pageInfo;
}
```

#### 5、修改文章是否公开

我们给每条文章是否公开的单选框添加事件，参数为当前文章的id号和单选框的value值即可，编写函数，发送异步修改文章是否公开状态

![图片19](/images/buildpro101/图片19.png)

change函数

```js
//定义改变是否公开函数
function change(aid,value) {
   $.get("/blog/article/isOpen",{
       'aid' : aid,
       'isOpen' : value
   },function (data) {
       layer.alert(data.mess, {icon:6});
   },'json');
}
```

后台Controller

```java
//异步修改文章是否公开
@RequestMapping("/article/isOpen")
@ResponseBody
public ResultVo isOpen(Article article){
    ResultVo resultVo = new ResultVo();
    try {
        articleService.isOpen(article);
        resultVo.setOk(true);
        if(article.getIsOpen().equals("0")){
            resultVo.setMess("文章已私密");
        }else{
            resultVo.setMess("文章已公开");
        }
    }catch (BlogException e){
        resultVo.setMess(e.getMessage());
    }
    return resultVo;
}
```

Servic代码

```java
@Override
public void isOpen(Article article) {
    int count = articleMapper.updateByPrimaryKeySelective(article);
    if(count == 0){
        throw new BlogException(BlogEnum.USER_LOGIN_CODE);
    }
}
```

#### 6、文章的添加

##### 6.1 Editormd介绍和上传图片

Editormd介绍

Editormd支持“标准” Markdown / CommonMark 和 Github 风格的语法，也可变身为代码编辑器，支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能

![图片20](/images/buildpro101/图片20.png)

上传图片

 在项目中导入Editormd插件

![图片21](/images/buildpro101/图片21.png)

编写上传图片的模板js代码

```js
var editor;
$(function() {
    editor = editormd("test-editor", {
        width  : "100%",
        height : "500px",
        path   : "/blog/editormd/lib/",//第三方依赖库
        saveHTMLToTextarea : true,//获取用户编辑的内容，将其放入到textarea中
        emoji: true,//emoji表情，默认关闭
        syncScrolling : "single",
        /*上传文件配置*/
        imageUpload : true,//开启上传文件功能
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],//上传图片格式
        imageUploadURL : "/blog/editorUpload",//后端上传图片服务地址
    });
});
```

编写上传文件的Controller

```java
//解决Editormd文件上传
@RequestMapping("/editorUpload")
@ResponseBody
public Map<String,Object> editorUpload(
        @RequestParam(value = "editormd-image-file", required = false)MultipartFile img,
        HttpSession session){
    Map<String, Object> map = FileUploadUtil.fileUpload(img, session);
    return map;
}
```

编写用于上传文件的工具类 FileUploadUtil

```java
//上传md的图片
public static Map<String,Object> fileUpload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile img,
                                     HttpSession session){
    /*
    准备把上传图片保存在upload目录下，还有子目录 upload/时间/用户名/很多的图片
    */
    String realPath = session.getServletContext().getRealPath("/upload");
    realPath += File.separator + DateTimeUtil.getDate();
    //获取登录用户
    User user = (User) session.getAttribute("user");
    realPath += File.separator + user.getUsername();

    File file = new File(realPath);
    if(!file.exists()){
        //创建带层级的目录 mkdir:只能创建一级目录
        file.mkdirs();
    }
    //相同用户可能会上传相同的文件名的图片，防止文件重名
    //获取用户名
    String fileName = img.getOriginalFilename();

    //1223434324文件名.png/jpg
    fileName = System.currentTimeMillis() + fileName;

    //定义用于给Editormd返回的map数据
    Map<String,Object> map = new HashMap<>();
    //获取回调地址
    String url = "http://localhost/blog/upload/" + DateTimeUtil.getDate()
            +File.separator + user.getUsername() + File.separator + fileName;
    try {
        img.transferTo(new File(realPath + File.separator + fileName));
        //返回success:1(数字) url:图片回调地址(图片在服务器存储路径)
        map.put("success",1);
        map.put("url",url);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return map;
}
```

效果如下图

![图片22](/images/buildpro101/图片22.png)

##### 6.2 上传文章logo

添加异步上传 js 文件

![图片23](/images/buildpro101/图片23.png)

编写异步上传js

```js
//上传文章logo
//异步上传文件
$('#img').change(function () {
    $.ajaxFileUpload({
            url: '/blog/fileUpload', //用于文件上传的服务器端请求地址
            fileElementId: 'img', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            success: function (data, status) {
                if(data.success == 1){
                    alert(data.message);
                }
            },
        }
    )
    return false;
});
```

后台上传文件Controller添加代码如下

```java
//上传图片
@RequestMapping("/fileUpload")
@ResponseBody
public Map<String,Object> fileUpload(MultipartFile img, HttpSession session){
    Map<String, Object> map = FileUploadUtil.fileUpload(img, session);
    return map;
}
```

上传文件工具类著需要添加如下代码即可

![图片24](/images/buildpro101/图片24.png)

##### 6.3 异步加载所有栏目

发送查询所有栏目请求，拼接页面内容，如下图

![图片25](/images/buildpro101/图片25.png)

前台js

```js
//异步查询所有栏目，把返回的json串拼接到下拉框中
$.get("/blog/article/queryCategory",function (data) {
    //List<Category>
    for(var i = 0; i < data.length; i++){
        $('#categories').append("<option value="+data[i].cid+">"+data[i].cname+"</option>");
    }
},'json');
```

后台Controller

```java
//异步查询所有栏目
@RequestMapping("/article/queryCategory")
@ResponseBody
public List<Category> queryCategory(){
    List<Category> categories = articleService.queryCategory();
    return categories;
}
```

Service代码

```java
@Override
public List<Category> queryCategory() {
    return categoryMapper.selectAll();
}
```

##### 6.4 查询栏目对应的标签

选中栏目后发送异步请求，查询指定栏目下的所有标签，下过如下图

![图片26](/images/buildpro101/图片26.png)

前台js

```js
//选中栏目，加载栏目下对应的标签
$('#categories').change(function () {
    $.get("/blog/article/queryTags",{'cid':$(this).val()},function (data) {
        //List<Tag>
        //清空内容
        $('#tags').html("");
        for(var i = 0; i < data.length; i++){
            $('#tags').append("<input type='checkbox' />"+data[i].tname+"&nbsp;&nbsp;&nbsp;");
        }
    },'json');
});
```

后台Controller

```java
@RequestMapping("/article/queryTags")
@ResponseBody
public List<Tag> queryTags(String cid){
    List<Tag> tags = articleService.queryTags(cid);
    return tags;
}
```

Service代码

```java
@Override
public List<Tag> queryTags(String cid) {
    Tag tag = new Tag();
    tag.setCid(cid);
    return tagMapper.select(tag);
}
```

