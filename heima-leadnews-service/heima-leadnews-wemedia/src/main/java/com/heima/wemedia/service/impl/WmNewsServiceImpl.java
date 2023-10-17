package com.heima.wemedia.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.utils.thread.WmThreadLocalUtil;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.service.WmNewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {
    /**
     * 条件查询文章列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findList(WmNewsPageReqDto dto) {
        dto.checkParam();

        IPage page = new Page(dto.getPage(), dto.getSize());

        LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (dto.getStatus() != null) {
            lambdaQueryWrapper.eq(WmNews::getStatus, dto.getStatus());
        }
        if (dto.getChannelId() != null) {
            lambdaQueryWrapper.eq(WmNews::getChannelId, dto.getChannelId());
        }
        if (dto.getBeginPubDate() != null && dto.getEndPubDate() != null) {
            lambdaQueryWrapper.between(WmNews::getPublishTime, dto.getBeginPubDate(), dto.getEndPubDate());
        }
        if (StringUtils.isNoneBlank(dto.getKeyword())) {
            lambdaQueryWrapper.like(WmNews::getTitle, dto.getKeyword());
        }
        lambdaQueryWrapper.eq(WmNews::getUserId, WmThreadLocalUtil.getUser().getId());

        lambdaQueryWrapper.orderByDesc(WmNews::getPublishTime);

        page = page(page, lambdaQueryWrapper);

        PageResponseResult pageResponseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        pageResponseResult.setData(page.getRecords());
        return pageResponseResult;
    }
}