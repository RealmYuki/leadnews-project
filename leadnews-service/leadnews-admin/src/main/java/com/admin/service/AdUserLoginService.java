package com.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.admin.dtos.AdUserDto;
import com.model.admin.pojos.AdUser;
import com.model.common.dtos.ResponseResult;

/**
 * ClassName: AdUserLoginService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/16 11:11
 * {@code @Version}  1.0
 */
public interface AdUserLoginService extends IService<AdUser> {

    /**
     * 管理端用户登录
     * @param dto
     * @return
     */
    public ResponseResult login(AdUserDto dto);
}
