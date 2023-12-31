package com.wemedia.service;

import com.model.common.dtos.ResponseResult;
import com.model.wemedia.pojos.WmNews;

/**
 * ClassName: WmNewsAutoScanService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/8 13:58
 * {@code @Version}  1.0
 */
public interface WmNewsAutoScanService {


    /**
     * 自媒体文章审核
     * @param id  自媒体文章id
     */
    public void autoScanWmNews(Integer id);

    public ResponseResult saveAppArticle(WmNews wmNews);


}
