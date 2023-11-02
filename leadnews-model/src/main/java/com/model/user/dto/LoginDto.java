package com.model.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: LoginDto
 * Description: 登录用前端传参
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/2 12:01
 * {@code @Version}  1.0
 */
@Data
public class LoginDto {
    @ApiModelProperty(value="手机号",required = true)
    private String phone;

    @ApiModelProperty(value="密码",required = true)
    private String password;
}
