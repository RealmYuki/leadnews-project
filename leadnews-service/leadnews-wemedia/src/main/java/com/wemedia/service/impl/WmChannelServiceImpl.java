package com.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.model.common.dtos.ResponseResult;
import com.model.wemedia.pojos.WmChannel;
import com.wemedia.mapper.WmChannelMapper;
import com.wemedia.service.WmChannelService;
import org.springframework.stereotype.Service;

/**
 * ClassName: WmChannelServiceImpl
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/7 14:35
 * {@code @Version}  1.0
 */
@Service
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {
    @Override
    public ResponseResult findAll() {
        return ResponseResult.okResult(list());
    }
}
