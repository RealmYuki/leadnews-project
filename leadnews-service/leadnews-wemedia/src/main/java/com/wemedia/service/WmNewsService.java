package com.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.common.dtos.ResponseResult;
import com.model.wemedia.dtos.NewsAuthDto;
import com.model.wemedia.dtos.WmNewsDto;
import com.model.wemedia.dtos.WmNewsPageReqDto;
import com.model.wemedia.dtos.WmNewsUpOrDownDto;
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

    /**
     *  发布文章或保存草稿
     * @param dto
     * @return
     */
    public ResponseResult submitNews(WmNewsDto dto);

    /**
     * 查询单个文章的信息
     * @param id
     * @return
     */
    ResponseResult findOne(Long id);

    /**
     * 删除文章
     * @param id
     * @return
     */
    ResponseResult deleteNews(Long id);

    /**
     * 文章的上下架
     * @param dto
     * @return
     */
    public ResponseResult downOrUp(WmNewsUpOrDownDto dto);

    /**
     * 管理端查询文章列表
     * @param dto
     * @return
     */
    ResponseResult adminList(NewsAuthDto dto);

    /**
     * 管理端查看指定文章
     * @param id
     * @return
     */
    ResponseResult adminFindOne(Long id);

    /**
     * 人工审核驳回
     * @param dto
     * @return
     */
    ResponseResult adminAuthFail(NewsAuthDto dto);

    /**
     * 人工审核通过
     * @param dto
     * @return
     */
    ResponseResult adminAuthPass(NewsAuthDto dto);
}
