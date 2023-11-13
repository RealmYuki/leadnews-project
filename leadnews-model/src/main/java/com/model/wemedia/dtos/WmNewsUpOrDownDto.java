package com.model.wemedia.dtos;

import lombok.Data;

/**
 * ClassName: WmNewsUpOrDownDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/13 15:22
 * {@code @Version}  1.0
 */
@Data
public class WmNewsUpOrDownDto {
    /**
     * 文章id
     */
    private Integer id;
    /**
     * 是否上架  0 下架  1 上架
     */
    private Short enable;
}
