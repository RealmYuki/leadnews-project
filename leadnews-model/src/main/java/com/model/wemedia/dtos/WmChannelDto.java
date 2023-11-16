package com.model.wemedia.dtos;

import com.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * ClassName: WmChannelDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/16 14:53
 * {@code @Version}  1.0
 */
@Data
public class WmChannelDto extends PageRequestDto {
    private String name;
}
