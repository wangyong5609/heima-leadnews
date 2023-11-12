package com.heima.xxljob.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HelloJob {

    @Value("${server.port}")
    private String port;

    @XxlJob("demoJobHandler")
    public void helloJob() {
        System.out.println("简单任务执行了。。。。" + port);

    }

    @XxlJob("shardingJobHandler")
    public void shardingJob() {

        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        for (Integer i : list) {
            if (i % shardTotal == shardIndex) {
                System.out.println("当前任务 " + i + " 由分片 " + shardIndex + " 执行");
            }
        }

    }
}