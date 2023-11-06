package com.wemedia.controller.v1;

import com.model.common.dtos.ResponseResult;
import com.model.wemedia.dtos.WmMaterialDto;
import com.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: WmMaterialController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/6 15:58
 * {@code @Version}  1.0
 */
@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController {


    @Autowired
    private WmMaterialService wmMaterialService;


    @PostMapping("/upload_picture")
    public ResponseResult uploadPicture(MultipartFile multipartFile){
        return wmMaterialService.uploadPicture(multipartFile);
    }

    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmMaterialDto dto){
        return wmMaterialService.findList(dto);
    }

    @GetMapping("/del_picture/{id}")
    public ResponseResult deletePicture(@PathVariable("id") Long id){
        return wmMaterialService.deletePicture(id);
    }

    @GetMapping("/collect/{id}")
    public ResponseResult collectPicture(@PathVariable("id") Long id){
        return wmMaterialService.collectPicture(id, (short) 1);
    }

    @GetMapping("/cancel_collect/{id}")
    public ResponseResult cancelCollectPicture(@PathVariable("id") Long id){
        return wmMaterialService.collectPicture(id, (short) 0);
    }

}
