package com.article.service;

import com.model.article.pojo.ApArticle;

/**
 * ClassName: ArticleFreemarkerService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/8 15:31
 * {@code @Version}  1.0
 */
public interface ArticleFreemarkerService {
    /**
     * 生成静态文件上传到minIO中
     * @param apArticle
     * @param content
     */
    public void buildArticleToMinIO(ApArticle apArticle, String content);
}
