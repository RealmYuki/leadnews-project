package com.user.service;

import com.model.common.dtos.ResponseResult;
import com.model.user.dto.UserRelationDto;

/**
 * ClassName: ApFollowService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 16:36
 * {@code @Version}  1.0
 */
public interface ApFollowService {

    /**
     * 用户关注和取消关注
     * @param dto
     * @return
     */
    ResponseResult follow(UserRelationDto dto);
}
