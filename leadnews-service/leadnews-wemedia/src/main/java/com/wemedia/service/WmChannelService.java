package com.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.common.dtos.ResponseResult;
import com.model.wemedia.pojos.WmChannel;

/**
 * ClassName: WmChannelMapper
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/7 14:35
 * {@code @Version}  1.0
 */
public interface WmChannelService extends IService<WmChannel> {

    /**
     * 查询所有频道
     * @return
     */
    public ResponseResult findAll();


}