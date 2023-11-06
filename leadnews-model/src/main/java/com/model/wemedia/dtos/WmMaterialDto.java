package com.model.wemedia.dtos;

import com.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * ClassName: WmMaterialDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/6 16:26
 * {@code @Version}  1.0
 */
@Data
public class WmMaterialDto extends PageRequestDto {

    /**
     * 1 收藏
     * 0 未收藏
     */
    private Short isCollection;
}
