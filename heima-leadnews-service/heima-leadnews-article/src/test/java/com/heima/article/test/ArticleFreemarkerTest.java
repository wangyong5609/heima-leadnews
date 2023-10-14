package com.heima.article.test;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.ArticleApplication;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.service.ApArticleService;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

@SpringBootTest(classes = ArticleApplication.class)
@RunWith(SpringRunner.class)
public class ArticleFreemarkerTest {

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;
    @Autowired
    private ApArticleService apArticleService;
    @Autowired
    private Configuration configuration;
    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void createStaticUrlTest() throws IOException, TemplateException {
        long articleId = 1383827888816836609L;
        LambdaQueryWrapper<ApArticleContent> wrapper = Wrappers.lambdaQuery(ApArticleContent.class).eq(ApArticleContent::getArticleId, articleId);
        // 1. 获取文章内容
        ApArticleContent apArticleContent = apArticleContentMapper.selectOne(wrapper);
        if (apArticleContent == null || StringUtils.isBlank(apArticleContent.getContent())) {
            System.out.println("文章内容无效");
            return;
        }

        // 2. 文章内容通过freemarker生成html文件
        Template template = configuration.getTemplate("article.ftl");
        HashMap<String, Object> content = new HashMap<>();
        content.put("content", JSONArray.parseArray(apArticleContent.getContent()));
        StringWriter out = new StringWriter();
        template.process(content, out);

        // 3. 把html文件上传到minio
        ByteArrayInputStream in = new ByteArrayInputStream(out.toString().getBytes());
        String path = fileStorageService.uploadHtmlFile("", apArticleContent.getArticleId() + ".html", in);

        // 4. 修改ap_article，保存static_url字段
        apArticleService.update(Wrappers.<ApArticle>lambdaUpdate().eq(ApArticle::getId, apArticleContent.getArticleId())
                .set(ApArticle::getStaticUrl, path));

    }
}
