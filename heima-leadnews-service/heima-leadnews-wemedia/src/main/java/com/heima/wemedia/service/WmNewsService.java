package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.NewsAuthDto;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;

public interface WmNewsService extends IService<WmNews> {

    ResponseResult findList(WmNewsPageReqDto dto);

    /**
     * 发布文章或保存草稿
     *
     * @param dto
     * @return
     */
    ResponseResult submitNews(WmNewsDto dto);

    /**
     * 文章的上下架
     *
     * @param dto
     * @return
     */
    ResponseResult downOrUp(WmNewsDto dto);


    //查询文章详情
    public ResponseResult findOne(Integer id);

    //查询文章列表
    public ResponseResult findList(NewsAuthDto dto);

    //查询文章详情
    public ResponseResult findWmNewsVo(Integer id);

    //文章审核，修改状态 2 审核失败    4 审核成功
    public ResponseResult updateStatus(Short status, NewsAuthDto dto);
}
