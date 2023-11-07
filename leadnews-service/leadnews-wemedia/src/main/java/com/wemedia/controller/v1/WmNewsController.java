package com.wemedia.controller.v1;

import com.model.common.dtos.ResponseResult;
import com.model.wemedia.dtos.WmNewsPageReqDto;
import com.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: WmNewsController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/7 14:42
 * {@code @Version}  1.0
 */
@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {

    @Autowired
    private WmNewsService wmNewsService;

    @PostMapping("/list")
    public ResponseResult findAll(@RequestBody WmNewsPageReqDto dto){
        return wmNewsService.findAll(dto);
    }

}
