package com.heima.shedule.test;

import com.heima.common.redis.CacheService;
import com.heima.schedule.ScheduleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private CacheService cacheService;

    @Test
    public void testList() {
        cacheService.lLeftPush("list1", "hello");

        String list1 = cacheService.lRightPop("list1");
        System.out.println(list1);
    }

    @Test
    public void testZSet() {
//        cacheService.zAdd("z_001", "001", 70);
//        cacheService.zAdd("z_001", "002", 60);
//        cacheService.zAdd("z_001", "003", 80);
//        cacheService.zAdd("z_001", "004", 90);

        Set<String> z001 = cacheService.zRangeByScore("z_001", 0, 80);
        System.out.println(z001);
    }
}
