首先，创建 `/data/prometheus/` 目录，然后创建 `prometheus.yml`，填入如下内容：

```yaml
global:
  scrape_interval:     15s
  evaluation_interval: 15s
 
alerting:
  alertmanagers:
  - static_configs:
    - targets: ['192.168.88.11:9093']
 
rule_files:
  - "node_down.yml"
 
scrape_configs:
 
  - job_name: 'prometheus'
    static_configs:
    - targets: ['192.168.88.11:9090']
 
  - job_name: 'node'
    scrape_interval: 8s
    static_configs:
    - targets: ['192.168.88.11:9100', '192.168.88.12:9100']
 
  - job_name: 'cadvisor'
    scrape_interval: 8s
    static_configs:
    - targets: ['192.168.88.11:8088', '192.168.88.12:8088']

```

接着进行创建 `node_down.yml`, 添加如下内容：

```yaml
groups:
- name: node_down
  rules:
  - alert: InstanceDown
    expr: up == 0
    for: 1m
    labels:
      user: test
    annotations:
      summary: "Instance {{ $labels.instance }} down"
      description: "{{ $labels.instance }} of job {{ $labels.job }} has been down for more than 1 minutes."
```

### 创建服务端的 docker-compose (docker01)

继续在 docker01 中 `/docker/prometheus/` 目录中创建 `docker-compose-prometheus.yml`, 添加如下内容：

```yaml
version: '2'
 
networks:
    monitor:
        driver: bridge
 
services:
    prometheus:
        image: prom/prometheus
        container_name: prometheus
        hostname: prometheus
        restart: always
        volumes:
            - /docker/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml
            - /docker/prometheus/node_down.yml:/etc/prometheus/node_down.yml
        ports:
            - "9090:9090"
        networks:
            - monitor
 
    grafana:
        image: grafana/grafana
        container_name: grafana
        hostname: grafana
        restart: always
        ports:
            - "3000:3000"
        networks:
            - monitor
 
    node-exporter:
        image: quay.io/prometheus/node-exporter
        container_name: node-exporter
        hostname: node-exporter
        restart: always
        ports:
            - "9100:9100"
        networks:
            - monitor
 
    cadvisor:
        image: google/cadvisor:latest
        container_name: cadvisor
        hostname: cadvisor
        restart: always
        volumes:
            - /:/rootfs:ro
            - /var/run:/var/run:rw
            - /sys:/sys:ro
            - /var/lib/docker/:/var/lib/docker:ro
        ports:
            - "8088:8080"
        networks:
            - monitor

```

### 创建客户端的 docker-compose (docker02)

继续在 `/docker/prometheus/` 目录中创建 `docker-compose.yml`, 添加如下内容：

~~~yaml
version: '2'
 
networks:
    monitor:
        driver: bridge
 
services:
    node-exporter:
        image: quay.io/prometheus/node-exporter
        container_name: node-exporter
        hostname: node-exporter
        restart: always
        ports:
            - "9100:9100"
        networks:
            - monitor
 
    cadvisor:
        image: google/cadvisor:latest
        container_name: cadvisor
        hostname: cadvisor
        restart: always
        volumes:
            - /:/rootfs:ro
            - /var/run:/var/run:rw
            - /sys:/sys:ro
            - /var/lib/docker/:/var/lib/docker:ro
        ports:
            - "8088:8080"
        networks:
            - monitor

~~~

## 启动 docker-compose

使用下面的命令启动 docker-compose 定义的容器

~~~bash
# docker01
docker-compose -f /docker/prometheus/docker-compose-prometheus.yml up -d
 
# docker02
docker-compose -f /docker/prometheus/docker-compose.yml up -d
~~~

--todo