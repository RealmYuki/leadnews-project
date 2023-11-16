package com.model.wemedia.dtos;

import com.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * ClassName: NewsAuthDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/16 16:10
 * {@code @Version}  1.0
 */
@Data
public class NewsAuthDto extends PageRequestDto {
    private Integer id;
    private String msg;
    private Integer status;
    private String title;
}
