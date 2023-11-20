package com.model.behavior.dtos;

import com.model.common.annotation.IdEncrypt;
import lombok.Data;

/**
 * ClassName: UnlikeBehaviorDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 16:12
 * {@code @Version}  1.0
 */
@Data
public class UnlikeBehaviorDto {
    @IdEncrypt
    private Long articleId;

    private short type;
}
