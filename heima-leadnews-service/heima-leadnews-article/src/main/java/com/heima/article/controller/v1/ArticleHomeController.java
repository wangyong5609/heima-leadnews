package com.heima.article.controller.v1;

import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
@Api(tags = "文章列表")
public class ArticleHomeController {
    @Autowired
    private ApArticleService apArticleService;

    /**
     * 加载首页
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "加载首页", notes = "")
    @PostMapping("load")
    public ResponseResult load(@RequestBody ArticleHomeDto dto) {
//        return apArticleService.load(dto, ArticleConstants.LOADTYPE_LOAD_MORE);
        return apArticleService.load2(dto, ArticleConstants.LOADTYPE_LOAD_MORE, true);
    }

    /**
     * 加载更多
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "加载更多", notes = "")
    @PostMapping("loadmore")
    public ResponseResult loadmore(@RequestBody ArticleHomeDto dto) {
        return apArticleService.load(dto, ArticleConstants.LOADTYPE_LOAD_MORE);
    }

    /**
     * 加载最新
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "加载最新", notes = "")
    @PostMapping("loadnew")
    public ResponseResult loadnew(@RequestBody ArticleHomeDto dto) {
        return apArticleService.load(dto, ArticleConstants.LOADTYPE_LOAD_NEW);
    }
}
