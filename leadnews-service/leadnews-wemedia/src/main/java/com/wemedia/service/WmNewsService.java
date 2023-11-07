package com.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.common.dtos.ResponseResult;
import com.model.wemedia.dtos.WmNewsPageReqDto;
import com.model.wemedia.pojos.WmNews;

/**
 * ClassName: WmNewsService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/7 14:44
 * {@code @Version}  1.0
 */
public interface WmNewsService extends IService<WmNews> {

    /**
     * 查询文章
     * @param dto
     * @return
     */
    public ResponseResult findAll(WmNewsPageReqDto dto);

}