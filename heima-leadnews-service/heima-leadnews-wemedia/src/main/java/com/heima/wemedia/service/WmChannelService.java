package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.ChannelDto;
import com.heima.model.wemedia.pojos.WmChannel;

public interface WmChannelService extends IService<WmChannel> {

    ResponseResult findAll();

    //保存
    ResponseResult insert(WmChannel wmChannel);

    //查询 
    ResponseResult findByNameAndPage(ChannelDto dto);

    //修改
    ResponseResult update(WmChannel wmChannel);

    //删除
    ResponseResult delete(Integer id);
}