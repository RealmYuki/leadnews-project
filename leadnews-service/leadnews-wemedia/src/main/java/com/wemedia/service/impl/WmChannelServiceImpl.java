package com.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.common.dtos.PageResponseResult;
import com.model.common.dtos.ResponseResult;
import com.model.common.enums.AppHttpCodeEnum;
import com.model.wemedia.dtos.WmChannelDto;
import com.model.wemedia.pojos.WmChannel;
import com.model.wemedia.pojos.WmNews;
import com.wemedia.mapper.WmChannelMapper;
import com.wemedia.service.WmChannelService;
import com.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.model.common.enums.AppHttpCodeEnum.WM_CHANNEL_DATA_ERROR;

/**
 * ClassName: WmChannelServiceImpl
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/7 14:35
 * {@code @Version}  1.0
 */
@Service
@Transactional
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {
    @Override
    public ResponseResult findAll() {
        LambdaQueryWrapper<WmChannel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(WmChannel::getStatus,true).orderByAsc(WmChannel::getOrd);
        return ResponseResult.okResult(list(lambdaQueryWrapper));
    }

    @Override
    public ResponseResult findList(WmChannelDto dto) {
        dto.checkParam();
        //分页查询
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<WmChannel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (dto.getName() != null) {
            lambdaQueryWrapper.like(WmChannel::getName, dto.getName());
        }
        lambdaQueryWrapper.orderByAsc(WmChannel::getOrd).orderByDesc(WmChannel::getCreatedTime);
        page = page(page, lambdaQueryWrapper);
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;

    }

    @Override
    public ResponseResult delete(Long id) {
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmChannel wmChannel = getById(id);
        if (wmChannel.getStatus()) {
            return ResponseResult.errorResult(WM_CHANNEL_DATA_ERROR, "频道未被禁用,无法删除!");
        }
        return ResponseResult.okResult(removeById(id));
    }

    @Override
    public ResponseResult insert(WmChannel wmChannel) {
        if (wmChannel == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (wmChannel.getName() == null || "".equals(wmChannel.getName()) || wmChannel.getStatus() == null || wmChannel.getOrd() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        wmChannel.setCreatedTime(new Date());
        return ResponseResult.okResult(save(wmChannel));
    }

    @Autowired
    private WmNewsService wmNewsService;

    @Override
    public ResponseResult update(WmChannel wmChannel) {
        if (wmChannel == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (wmChannel.getName() == null || "".equals(wmChannel.getName()) || wmChannel.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (!wmChannel.getStatus()){
            LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(WmNews::getChannelId, wmChannel.getId());
            List<WmNews> list = wmNewsService.list(lambdaQueryWrapper);
            if (list.size() > 0) {
                return ResponseResult.errorResult(WM_CHANNEL_DATA_ERROR, "频道已被引用,无法禁用");
            }
        }

        return ResponseResult.okResult(updateById(wmChannel));
    }
}
