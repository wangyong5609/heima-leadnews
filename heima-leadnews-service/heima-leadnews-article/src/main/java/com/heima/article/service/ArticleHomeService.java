package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dtos.ArticleInfoDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;

public interface ArticleHomeService extends IService<ApArticle> {

    //加载文章详情 数据回显
    ResponseResult loadArticleBehavior(ArticleInfoDto dto);
}