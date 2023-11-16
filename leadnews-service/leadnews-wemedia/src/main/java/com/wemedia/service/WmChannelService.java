package com.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.common.dtos.ResponseResult;
import com.model.wemedia.dtos.WmChannelDto;
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
     * 自媒体端查询所有频道
     * @return
     */
    public ResponseResult findAll();

    /**
     * 管理端查询频道列表
     * @param dto
     * @return
     */
    ResponseResult findList(WmChannelDto dto);

    /**
     * 根据id删除频道
     * @param id
     * @return
     */
    ResponseResult delete(Long id);

    /**
     * 增加频道
     * @param wmChannel
     * @return
     */
    ResponseResult insert(WmChannel wmChannel);

    /**
     * 更改频道
     * @param wmChannel
     * @return
     */
    ResponseResult update(WmChannel wmChannel);
}