package com.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.common.dtos.ResponseResult;
import com.model.wemedia.dtos.WmSensitiveDto;
import com.model.wemedia.pojos.WmSensitive;

/**
 * ClassName: WmSensitiveService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/16 14:12
 * {@code @Version}  1.0
 */
public interface WmSensitiveService extends IService<WmSensitive> {

    /**
     * 查询违禁词列表
     * @param dto
     * @return
     */
    ResponseResult findList(WmSensitiveDto dto);

    /**
     * 添加违禁词
     * @param wmSensitive
     * @return
     */
    ResponseResult insert(WmSensitive wmSensitive);

    /**
     * 修改违禁词
     * @param wmSensitive
     * @return
     */
    ResponseResult update(WmSensitive wmSensitive);
}
