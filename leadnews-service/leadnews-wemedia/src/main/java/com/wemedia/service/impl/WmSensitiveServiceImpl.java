package com.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.common.dtos.PageResponseResult;
import com.model.common.dtos.ResponseResult;
import com.model.common.enums.AppHttpCodeEnum;
import com.model.wemedia.dtos.WmSensitiveDto;
import com.model.wemedia.pojos.WmMaterial;
import com.model.wemedia.pojos.WmSensitive;
import com.wemedia.mapper.WmSensitiveMapper;
import com.wemedia.service.WmSensitiveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * ClassName: WmSensitiveServiceImpl
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/16 14:13
 * {@code @Version}  1.0
 */
@Service
@Transactional
public class WmSensitiveServiceImpl extends ServiceImpl<WmSensitiveMapper, WmSensitive> implements WmSensitiveService {
    @Override
    public ResponseResult findList(WmSensitiveDto dto) {
        dto.checkParam();
        //分页查询
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<WmSensitive> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (dto.getName() != null) {
            lambdaQueryWrapper.like(WmSensitive::getSensitives,dto.getName());
        }
        lambdaQueryWrapper.orderByDesc(WmSensitive::getCreatedTime);
        page = page(page, lambdaQueryWrapper);
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult insert(WmSensitive wmSensitive) {
        if (wmSensitive==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        if (wmSensitive.getSensitives()==null|| "".equals(wmSensitive.getSensitives())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        wmSensitive.setCreatedTime(new Date());
        return ResponseResult.okResult(save(wmSensitive));
    }

    @Override
    public ResponseResult update(WmSensitive wmSensitive) {
        if (wmSensitive==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        if (wmSensitive.getSensitives()==null|| "".equals(wmSensitive.getSensitives())||wmSensitive.getId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        return ResponseResult.okResult(updateById(wmSensitive));
    }
}
