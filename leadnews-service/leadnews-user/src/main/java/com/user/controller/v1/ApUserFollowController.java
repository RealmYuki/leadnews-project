package com.user.controller.v1;

import com.model.common.dtos.ResponseResult;
import com.model.user.dto.UserRelationDto;
import com.user.service.ApFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ApUserFollowController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 16:23
 * {@code @Version}  1.0
 */

@RestController
@RequestMapping("/api/v1/user")
public class ApUserFollowController {

    @Autowired
    private ApFollowService apFollowService;

    @PostMapping("/user_follow")
    public ResponseResult userFollow(@RequestBody UserRelationDto dto){
        return apFollowService.follow(dto);
    }

}
