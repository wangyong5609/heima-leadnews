官方文档：https://min.io/docs/minio/container/index.html
Java案例：https://min.io/docs/minio/linux/developers/java/minio-java.html

docker安装方式

docker run \
-p 9000:9000 \
-p 9090:9090 \
--name minio \
-d --restart=always \
-v /docker/data:/data \
-e "MINIO_ROOT_USER=minio" \
-e "MINIO_ROOT_PASSWORD=minio123" \
quay.io/minio/minio server /data --console-address ":9090"

# 遇到的问题
1. 上传文件报错：message = The difference between the request time and the server's time is too large
因为我的minio是部署在虚拟机上的，虚拟机的时间和本机时间相差太多，所以报错了
解决方案：https://www.cnblogs.com/stonechen/p/14334351.html
2. 上传后的文件无法访问
解决方案：https://blog.csdn.net/qq_38377190/article/details/125381845
