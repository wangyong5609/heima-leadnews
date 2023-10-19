package com.heima.wemedia.test;

import com.heima.common.aliyun.GreenImageScan;
import com.heima.common.aliyun.GreenTextScan;
import com.heima.file.service.FileStorageService;
import com.heima.wemedia.WemediaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = WemediaApplication.class)
@RunWith(SpringRunner.class)
public class AliyunTest {
    @Autowired
    private GreenTextScan greenTextScan;
    @Autowired
    private GreenImageScan greenImageScan;

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void testScanText() throws Exception {
        // 测试文本审核
        Map greeTextScan = greenTextScan.greeTextScan("冰毒");
        System.out.println(greeTextScan);
    }

    @Test
    public void testScanImage() throws Exception {
        // 测试图片审核
        // 从数据库选择一张图片
        byte[] bytes = fileStorageService.downLoadFile("http://192.168.88.11:9000/leadnews/2023/10/19/01b466fefca346758af7eb755116c6c1.png");
        List<byte[]> list = new ArrayList<>();
        list.add(bytes);

        Map map = greenImageScan.imageScan(list);
        System.out.println(map);

    }
}
