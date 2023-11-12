package com.heima.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {
    /**
     * 加载文章列表
     *
     * @param dto
     * @param type 1 加载更多 2加载最新
     * @return
     */
    List<ApArticle> loadArticleList(ArticleHomeDto dto, Short type);

    /**
     * 查询最近5天发布的文章
     *
     * @param dayParam
     * @return
     */
    List<ApArticle> findArticleListByLast5days(@Param("dayParam") Date dayParam);
}
