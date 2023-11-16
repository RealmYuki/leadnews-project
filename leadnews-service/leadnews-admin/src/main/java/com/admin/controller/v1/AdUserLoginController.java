package com.admin.controller.v1;

import com.admin.service.AdUserLoginService;
import com.model.admin.dtos.AdUserDto;
import com.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: AdUserLoginController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/16 11:09
 * {@code @Version}  1.0
 */
@RestController
@RequestMapping("/login")
public class AdUserLoginController {

    @Autowired
    private AdUserLoginService adUserLoginService;

    @PostMapping("/in")
    public ResponseResult login(@RequestBody AdUserDto adUserDto){
        return adUserLoginService.login(adUserDto);
    }
}
