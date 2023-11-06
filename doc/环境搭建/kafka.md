# kafka安装配置

Kafka对于zookeeper是强依赖，保存kafka相关的节点数据，所以安装Kafka之前必须先安装zookeeper, 版本号也不要轻易修改

- Docker安装zookeeper

下载镜像：

```shell
docker pull zookeeper:3.4.14
```

创建容器

```shell
docker run -d --name zookeeper -p 2181:2181 --restart=always zookeeper:3.4.14
```

- Docker安装kafka

下载镜像：

```shell
docker pull wurstmeister/kafka:2.12-2.3.1
```

创建容器

```shell
docker run -d --name kafka --restart=always \
--env KAFKA_ADVERTISED_HOST_NAME=192.168.88.11 \
--env KAFKA_ZOOKEEPER_CONNECT=192.168.88.11:2181 \
--env KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.88.11:9092 \
--env KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 \
--env KAFKA_HEAP_OPTS="-Xmx256M -Xms256M" \
--net=host wurstmeister/kafka:2.12-2.3.1
```

### 