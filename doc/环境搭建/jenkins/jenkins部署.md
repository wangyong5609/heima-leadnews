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

解决：删除java:8镜像，重新拉取

6. Jenkins从本地仓库拉取镜像创建容器报错：docker: Error response from daemon: Get "https://192.168.88.12:5000/v2/": http:
   server gave HTTP response to HTTPS client.

这个错误通常是由于 Docker 默认使用 HTTPS 来与 Docker Registry 通信，但是你的 Docker Registry 可能只支持
HTTP。你可以通过以下步骤来解决这个问题：

1. 打开或创建 Docker 的守护进程配置文件。这个文件通常位于 `/etc/docker/daemon.json`。

```bash
sudo vi /etc/docker/daemon.json
```

1. 在这个文件中添加 `insecure-registries` 配置，指定你的 Docker Registry 的地址。

```json
{
  "insecure-registries": [
    "192.168.88.12:5000"
  ]
}
```

在这里，`192.168.88.12:5000` 是你的 Docker Registry 的地址。

1. 保存并关闭文件。
2. 重启 Docker 服务来使改动生效：

```bash
sudo systemctl restart docker
```

希望这些信息对你有所帮助！



