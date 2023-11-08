package com.model.article.dto;

import com.model.article.pojo.ApArticle;
import lombok.Data;

/**
 * ClassName: ArticleDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/8 11:09
 * {@code @Version}  1.0
 */
@Data
public class ArticleDto extends ApArticle {
    /**
     * 文章内容
     */
    private String content;
}
