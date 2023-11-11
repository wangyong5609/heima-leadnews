package com.heima.user.controller.v1;

import com.heima.common.constants.UserConstants;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.AuthDto;
import com.heima.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class ApUserRealnameController {

    @Autowired
    private ApUserRealnameService apUserRealnameService;

    //按照状态分页查询用户列表
    @PostMapping("/list")
    public ResponseResult loadListByStatus(@RequestBody AuthDto dto) {
        return apUserRealnameService.loadListByStatus(dto);
    }

    //审核成功
    @PostMapping("/authPass")
    public ResponseResult authPass(@RequestBody AuthDto dto) {
        return apUserRealnameService.updateStatus(dto, UserConstants.PASS_AUTH);
    }

    //审核失败
    @PostMapping("/authFail")
    public ResponseResult authFail(@RequestBody AuthDto dto) {
        return apUserRealnameService.updateStatus(dto, UserConstants.FAIL_AUTH);
    }
}
