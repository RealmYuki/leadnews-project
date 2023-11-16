package com.admin.service.impl;

import com.admin.mapper.AdUserLoginMapper;
import com.admin.service.AdUserLoginService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.admin.dtos.AdUserDto;
import com.model.admin.pojos.AdUser;
import com.model.common.dtos.ResponseResult;
import com.model.common.enums.AppHttpCodeEnum;
import com.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: AdUserLoginServiceImpl
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/16 11:12
 * {@code @Version}  1.0
 */
@Service
public class AdUserLoginServiceImpl extends ServiceImpl<AdUserLoginMapper, AdUser> implements AdUserLoginService {
    @Override
    public ResponseResult login(AdUserDto dto) {
        if (StringUtils.isBlank(dto.getPassword()) || StringUtils.isBlank(dto.getName())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE,"用户名或密码为空");
        }
        //查询用户
        AdUser adUser = getOne(Wrappers.<AdUser>lambdaQuery().eq(AdUser::getName, dto.getName()));
        if (adUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户不存在");
        }
        //比对密码
        String salt = adUser.getSalt();
        String password = dto.getPassword() + salt;
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(adUser.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        //返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("token", AppJwtUtil.getToken(adUser.getId().longValue()));
        adUser.setSalt("");
        adUser.setPassword("");
        map.put("user", adUser);
        return ResponseResult.okResult(map);
    }
}
