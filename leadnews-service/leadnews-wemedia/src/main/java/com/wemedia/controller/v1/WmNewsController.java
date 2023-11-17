package com.wemedia.controller.v1;

import com.model.common.dtos.ResponseResult;
import com.model.wemedia.dtos.NewsAuthDto;
import com.model.wemedia.dtos.WmNewsDto;
import com.model.wemedia.dtos.WmNewsPageReqDto;
import com.model.wemedia.dtos.WmNewsUpOrDownDto;
import com.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto dto){
        return wmNewsService.submitNews(dto);
    }

    @GetMapping("/one/{id}")
    public ResponseResult findOne(@PathVariable("id") Long id){
        return wmNewsService.findOne(id);
    }

    @GetMapping("/del_news/{id}")
    public ResponseResult deleteNews(@PathVariable("id") Long id){
        return wmNewsService.deleteNews(id);
    }

    @PostMapping("/down_or_up")
    public ResponseResult downOrUp(@RequestBody WmNewsUpOrDownDto dto){
        return wmNewsService.downOrUp(dto);
    }

    @PostMapping("/list_vo")
    public ResponseResult adminList(@RequestBody NewsAuthDto dto){
        return wmNewsService.adminList(dto);
    }

    @GetMapping("/one_vo/{id}")
    public ResponseResult adminFindOne(@PathVariable("id") Long id){
        return wmNewsService.adminFindOne(id);
    }

    @PostMapping("/auth_fail")
    public ResponseResult adminAuthFail(@RequestBody NewsAuthDto dto){
        return wmNewsService.adminAuthFail(dto);
    }

    @PostMapping("/auth_pass")
    public ResponseResult adminAuthPass(@RequestBody NewsAuthDto dto){
        return wmNewsService.adminAuthPass(dto);
    }

}
