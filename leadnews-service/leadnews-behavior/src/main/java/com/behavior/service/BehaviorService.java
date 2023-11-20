package com.behavior.service;

import com.model.behavior.dtos.LikesBehaviorDto;
import com.model.behavior.dtos.ReadBehaviorDto;
import com.model.behavior.dtos.UnlikeBehaviorDto;
import com.model.common.dtos.ResponseResult;

/**
 * ClassName: BehaviorService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 15:45
 * {@code @Version}  1.0
 */
public interface BehaviorService {

    /**
     * 记录 点赞 取消点赞
     * @param dto
     * @return
     */
    ResponseResult likesBehavior(LikesBehaviorDto dto);

    /**
     * 记录 阅读次数 阅读时长
     * @param dto
     * @return
     */
    ResponseResult readBehavior(ReadBehaviorDto dto);

    /**
     * 记录 不喜欢 取消不喜欢
     * @param dto
     * @return
     */
    ResponseResult unlikeBehavior(UnlikeBehaviorDto dto);
}
