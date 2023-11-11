package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.SensitiveDto;
import com.heima.model.wemedia.pojos.WmSensitive;

public interface WmSensitiveService extends IService<WmSensitive> {
    //查询
    ResponseResult list(SensitiveDto dto);

    //新增
    ResponseResult insert(WmSensitive wmSensitive);

    //修改
    ResponseResult update(WmSensitive wmSensitive);

    //删除
    ResponseResult delete(Integer id);
}
