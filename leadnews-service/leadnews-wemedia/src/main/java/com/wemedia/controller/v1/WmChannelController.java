package com.wemedia.controller.v1;

import com.model.common.dtos.ResponseResult;
import com.model.common.enums.AppHttpCodeEnum;
import com.model.wemedia.dtos.WmChannelDto;
import com.model.wemedia.dtos.WmSensitiveDto;
import com.model.wemedia.pojos.WmChannel;
import com.model.wemedia.pojos.WmSensitive;
import com.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmChannelDto dto){
        return wmChannelService.findList(dto);
    }

    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id){
        return wmChannelService.delete(id);
    }

    @PostMapping("/save")
    public ResponseResult save(@RequestBody WmChannel wmChannel){
        return wmChannelService.insert(wmChannel);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody WmChannel wmChannel){
        return wmChannelService.update(wmChannel);
    }

}