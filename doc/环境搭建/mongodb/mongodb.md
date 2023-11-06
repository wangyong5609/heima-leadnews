# 安装MongoDB

拉取镜像

```
docker pull mongo
```

创建容器

```
docker run -di --name mongo-service --restart=always -p 27017:27017 -v /docker/mongodb/data:/data mongo
```
