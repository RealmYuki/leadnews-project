package com.model.wemedia.dtos;

import com.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * ClassName: WmSensitiveDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/16 14:08
 * {@code @Version}  1.0
 */
@Data
public class WmSensitiveDto extends PageRequestDto {
    private String name;
}
