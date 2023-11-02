package com.user.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.common.dtos.ResponseResult;
import com.model.common.enums.AppHttpCodeEnum;
import com.model.user.dto.LoginDto;
import com.model.user.pojo.ApUser;
import com.user.mapper.ApUserMapper;
import com.user.service.ApUserService;
import com.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.Digest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ApUserServiceImpl
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/2 12:06
 * {@code @Version}  1.0
 */
@Service
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
    @Override
    public ResponseResult login(LoginDto dto) {
        if(!StringUtils.isBlank(dto.getPassword())&&!StringUtils.isBlank(dto.getPhone())){
            //查询用户
            ApUser apUser = getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, dto.getPhone()));
            if (apUser == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST,"用户不存在");
            }
            //比对密码
            String salt = apUser.getSalt();
            String password = dto.getPassword()+salt;
            password= DigestUtils.md5DigestAsHex(password.getBytes());
            if(!password.equals(apUser.getPassword())){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
            //返回数据
            Map<String,Object> map =new HashMap<>();
            map.put("token", AppJwtUtil.getToken(apUser.getId().longValue()));
            apUser.setSalt("");
            apUser.setPassword("");
            map.put("user",apUser);
            return ResponseResult.okResult(map);
        } else {
            //游客  同样返回token  id = 0
            Map<String, Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(0L));
            return ResponseResult.okResult(map);
        }
    }
}
