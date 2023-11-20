package com.model.behavior.dtos;

import com.model.common.annotation.IdEncrypt;
import lombok.Data;

/**
 * ClassName: LikesBehaviorDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 15:43
 * {@code @Version}  1.0
 */
@Data
public class LikesBehaviorDto {

    @IdEncrypt
    private Long articleId;
    private short operation;
    private short type;
}
