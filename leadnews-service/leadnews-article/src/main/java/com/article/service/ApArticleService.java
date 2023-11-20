package com.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.article.dto.ArticleDto;
import com.model.article.dto.ArticleHomeDto;
import com.model.article.dto.ArticleInfoDto;
import com.model.article.pojo.ApArticle;
import com.model.common.dtos.ResponseResult;

/**
 * ClassName: ApArticleService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/3 14:58
 * {@code @Version}  1.0
 */
public interface ApArticleService extends IService<ApArticle> {

    /**
     * 根据参数加载文章列表
     * @param loadtype 1为加载更多  2为加载最新
     * @param dto
     * @return
     */
    ResponseResult load(Short loadtype, ArticleHomeDto dto);

    /**
     * 保存app端相关文章
     * @param dto
     * @return
     */
    ResponseResult saveArticle(ArticleDto dto) ;

    /**
     * 加载文章行为，是否点赞 是否收藏等 数据
     * @param dto
     * @return
     */
    ResponseResult loadArticleBehavior(ArticleInfoDto dto);
}
