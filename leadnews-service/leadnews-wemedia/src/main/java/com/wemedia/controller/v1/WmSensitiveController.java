package com.wemedia.controller.v1;

import com.model.common.dtos.ResponseResult;
import com.model.common.enums.AppHttpCodeEnum;
import com.model.wemedia.dtos.WmSensitiveDto;
import com.model.wemedia.pojos.WmSensitive;
import com.wemedia.service.WmSensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: WmSensitiveController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/16 14:04
 * {@code @Version}  1.0
 */
@RestController
@RequestMapping("/api/v1/sensitive")
public class WmSensitiveController {

    @Autowired
    private WmSensitiveService wmSensitiveService;

    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmSensitiveDto sensitiveDto){
        return wmSensitiveService.findList(sensitiveDto);
    }

    @DeleteMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id){
        if (id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return ResponseResult.okResult(wmSensitiveService.removeById(id));
    }

    @PostMapping("/save")
    public ResponseResult save(@RequestBody WmSensitive wmSensitive){
        return wmSensitiveService.insert(wmSensitive);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody WmSensitive wmSensitive){
        return wmSensitiveService.update(wmSensitive);
    }

}
