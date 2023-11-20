package com.behavior.controller.v1;

import com.behavior.service.BehaviorService;
import com.model.behavior.dtos.LikesBehaviorDto;
import com.model.behavior.dtos.ReadBehaviorDto;
import com.model.behavior.dtos.UnlikeBehaviorDto;
import com.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: BehaviorController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 15:41
 * {@code @Version}  1.0
 */

@RestController
@RequestMapping("/api/v1")
public class BehaviorController {

    @Autowired
    private BehaviorService behaviorService;

    @PostMapping("/likes_behavior")
    public ResponseResult likesBehavior(@RequestBody LikesBehaviorDto dto){
        return behaviorService.likesBehavior(dto);
    }

    @PostMapping("/read_behavior")
    public ResponseResult readBehavior(@RequestBody ReadBehaviorDto dto){
        return behaviorService.readBehavior(dto);
    }

    @PostMapping("/un_likes_behavior")
    public ResponseResult unlikeBehavior(@RequestBody UnlikeBehaviorDto dto){
        return behaviorService.unlikeBehavior(dto);
    }


}
