Jenkin部署黑马头条

## 遇到的问题

1. 第一次build报错：Couldn’t find any revision to build. Verify the repository and branch configuration for this job

https://blog.csdn.net/qq_29974229/article/details/123974576

2. build时发现包从repo.maven.apache.org下载

```
Downloaded: https://repo.maven.apache.org/maven2/io/fabric8/kubernetes-client-bom/4.13.2/kubernetes-client-bom-4.13.2.pom (14 KB at 4.9 KB/sec)
Downloading: https://maven.aliyun.com/repository/spring/org/springframework/cloud/spring-cloud-gcp-dependencies/1.2.7.RELEASE/spring-cloud-gcp-dependencies-1.2.7.RELEASE.pom
           
Downloading: https://repo.maven.apache.org/maven2/org/springframework/cloud/spring-cloud-gcp-dependencies/1.2.7.RELEASE/spring-cloud-gcp-dependencies-1.2.7.RELEASE.pom
4/14 KB    
8/14 KB   
12/14 KB   
14/14 KB   
```

检查maven settings.xml 文件，配置国内镜像仓库，如阿里云

~~~
 <mirror>
      <id>aliyunmaven</id>
      <mirrorOf>*</mirrorOf>
      <name>阿里云公共仓库</name>
      <url>https://maven.aliyun.com/repository/public</url>
</mirror>
~~~

3. Jenkins build报错：[ERROR] [ERROR] Some problems were encountered while processing the POMs

~~~
[ERROR] [ERROR] Some problems were encountered while processing the POMs:
[FATAL] Non-parseable POM /usr/local/maven/apache-maven-3.3.9/repository_heima-leannews/com/fasterxml/jackson/jackson-bom/2.11.4/jackson-bom-2.11.4.pom: unexpected markup <!d (position: START_DOCUMENT seen \n<!d... @2:4)  @ /usr/local/maven/apache-maven-3.3.9/repository_heima-leannews/com/fasterxml/jackson/jackson-bom/2.11.4/jackson-bom-2.11.4.pom, line 2, column 4
 @ 
[ERROR] The build could not read 1 project -> [Help 1]
[ERROR]   
[ERROR]   The project com.heima:heima-leadnews:1.0-SNAPSHOT (/var/lib/jenkins/workspace/news/pom.xml) has 1 error
[ERROR]     Non-parseable POM /usr/local/maven/apache-maven-3.3.9/repository_heima-leannews/com/fasterxml/jackson/jackson-bom/2.11.4/jackson-bom-2.11.4.pom: unexpected markup <!d (position: START_DOCUMENT seen \n<!d... @2:4)  @ /usr/local/maven/apache-maven-3.3.9/repository_heima-leannews/com/fasterxml/jackson/jackson-bom/2.11.4/jackson-bom-2.11.4.pom, line 2, column 4 -> [Help 2]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/ProjectBuildingException
[ERROR] [Help 2] http://cwiki.apache.org/confluence/display/MAVEN/ModelParseException
~~~

解决：删除错误的文件重新导入

~~~
rm -rf /usr/local/maven/apache-maven-3.3.9/repository_heima-leannews/com/fasterxml/jackson/jackson-bom/2.11.4/
~~~

4. Jenkins build报错：子模块不存在

~~~
[ERROR] [ERROR] Some problems were encountered while processing the POMs:
[ERROR] Child module /var/lib/jenkins/workspace/news/heima-leadnews-test/tess4j-demo of /var/lib/jenkins/workspace/news/heima-leadnews-test/pom.xml does not exist @ 
 @ 
[ERROR] The build could not read 1 project -> [Help 1]
[ERROR]   
[ERROR]   The project com.heima:heima-leadnews-test:1.0-SNAPSHOT (/var/lib/jenkins/workspace/news/heima-leadnews-test/pom.xml) has 1 error
[ERROR]     Child module /var/lib/jenkins/workspace/news/heima-leadnews-test/tess4j-demo of /var/lib/jenkins/workspace/news/heima-leadnews-test/pom.xml does not exist
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
~~~

因为子模块的文件名和pom中的模块名不同导致

![image-20231118220202475](./jenkins%E9%83%A8%E7%BD%B2.assets/image-20231118220202475.png)

![image-20231118220707192](./jenkins%E9%83%A8%E7%BD%B2.assets/image-20231118220707192.png)

5. Jenkins build报错：Could not build image: manifest for java:8 not found

```
[ERROR] Failed to execute goal com.spotify:dockerfile-maven-plugin:1.3.6:build (default-cli) on project heima-leadnews-user: Could not build image: manifest for java:8 not found -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
```