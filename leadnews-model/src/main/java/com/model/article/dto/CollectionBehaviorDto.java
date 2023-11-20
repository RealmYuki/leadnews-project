package com.model.article.dto;

import com.model.common.annotation.IdEncrypt;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: CollectionBehaviorDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 16:49
 * {@code @Version}  1.0
 */
@Data
public class CollectionBehaviorDto {
    // 文章、动态ID
    @IdEncrypt
    Long entryId;
    /**
     * 收藏内容类型
     * 0 文章
     * 1 动态
     */
    Short type;

    /**
     * 操作类型
     * 0收藏
     * 1取消收藏
     */
    Short operation;

    Date publishedTime;

}
