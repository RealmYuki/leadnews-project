package com.wemedia.controller.v1;

import com.model.common.dtos.ResponseResult;
import com.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * ClassName: WmChannelController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/7 14:33
 * {@code @Version}  1.0
 */
@RestController
@RequestMapping("/api/v1/channel")
public class WmChannelController {

    @Autowired
    private WmChannelService wmChannelService;

    @GetMapping("/channels")
    public ResponseResult findAll(){
        return wmChannelService.findAll();
    }
}