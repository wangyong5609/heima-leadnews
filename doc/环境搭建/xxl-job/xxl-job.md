docker安装

```shell
docker run -e PARAMS="--spring.datasource.url=jdbc:mysql://192.168.88.11:3306/xxl_job?Unicode=true&characterEncoding=UTF-8 \
--spring.datasource.username=root \
--spring.datasource.password=root" \
-p 8888:8080 -v /docker/xxl-job:/data/applogs \
--name xxl-job-admin --restart=always  -d xuxueli/xxl-job-admin:2.3.0
```