官方文档：https://min.io/docs/minio/container/index.html

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