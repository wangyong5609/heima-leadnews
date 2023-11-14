```shell
docker pull jenkins/jenkins
```


```shell
mkdir -p /docker/jenkins
chmod 777 /docker/jenkins
```

```shell
docker run -d -uroot -p 9095:8080 -p 50000:50000 --name jenkins \
 -v /docker/jenkins:/var/jenkins_home \
 -v /etc/localtime:/etc/localtime jenkins/jenkins
```