package com.model.article.dto;

import lombok.Data;

import java.util.Date;

/**
 * ClassName: ArticleHomeDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/3 14:47
 * {@code @Version}  1.0
 */
@Data
public class ArticleHomeDto {

    // 最大时间
    Date maxBehotTime;
    // 最小时间
    Date minBehotTime;
    // 分页size
    Integer size;
    // 频道ID
    String tag;
}
