package com.model.behavior.dtos;

import com.model.common.annotation.IdEncrypt;
import lombok.Data;

/**
 * ClassName: ReadBehaviorDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 16:03
 * {@code @Version}  1.0
 */
@Data
public class ReadBehaviorDto {
    @IdEncrypt
    // 文章、动态、评论等ID
    Long articleId;

    /**
     * 阅读次数
     */
    Short count;

    /**
     * 阅读时长（S)
     */
    Integer readDuration;

    /**
     * 阅读百分比
     */
    Short percentage;

    /**
     * 加载时间
     */
    Short loadDuration;
}
