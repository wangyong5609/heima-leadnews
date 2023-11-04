package com.heima.kafka.sample;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        //1.添加kafka的配置信息
        Properties properties = new Properties();
        //kafka的连接地址
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.88.11:9092");
        //消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");
        //消息的反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // 提交方式
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        //2.消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        //3.订阅主题
        consumer.subscribe(Collections.singletonList("itheima-topic"));

        commitWithSyncAndAsync(consumer);
    }

    public static void commitWithSyncAndAsync(KafkaConsumer<String, String> consumer) {
        try {
            //当前线程一直处于监听状态
            while (true) {
                //4.获取消息
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(consumerRecord.key());
                    System.out.println(consumerRecord.value());
                    System.out.println(consumerRecord.offset());
                    System.out.println(consumerRecord.partition());
                    // 同步提交偏移量
                    consumer.commitAsync();
                }
            }
        } catch (Exception e) {
            System.out.println("异步提交失败" + e);
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    public static void syncCommit(KafkaConsumer<String, String> consumer) {
        //当前线程一直处于监听状态
        while (true) {
            //4.获取消息
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key());
                System.out.println(consumerRecord.value());
                System.out.println(consumerRecord.offset());
                System.out.println(consumerRecord.partition());
                // 同步提交偏移量
                try {
                    consumer.commitSync();
                } catch (Exception e) {
                    System.out.println("提交失败");
                }
            }
        }
    }

    public static void aSyncCommit(KafkaConsumer<String, String> consumer) {
        while (true) {
            //4.获取消息
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key());
                System.out.println(consumerRecord.value());
                System.out.println(consumerRecord.offset());
                System.out.println(consumerRecord.partition());

            }
            // 异步提交
            consumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                    if (e != null) {
                        System.out.println("异步提交失败偏移量：" + map + "异常" + e);
                    }
                }
            });
        }
    }

}
