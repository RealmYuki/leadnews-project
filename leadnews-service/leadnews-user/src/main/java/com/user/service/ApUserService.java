package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.common.dtos.ResponseResult;
import com.model.user.dto.LoginDto;
import com.model.user.pojo.ApUser;

/**
 * ClassName: ApUserService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/2 12:05
 * {@code @Version}  1.0
 */

public interface ApUserService extends IService<ApUser> {

    /**
     * app端登录
     * @param dto
     * @return
     */
    ResponseResult login(LoginDto dto);
}
