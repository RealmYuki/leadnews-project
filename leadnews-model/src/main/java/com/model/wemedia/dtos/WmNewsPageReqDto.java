package com.model.wemedia.dtos;

import com.model.common.dtos.PageRequestDto;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: WmNewsPageReqDto
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/7 14:42
 * {@code @Version}  1.0
 */
@Data
public class WmNewsPageReqDto extends PageRequestDto {

    /**
     * 状态
     */
    private Short status;
    /**
     * 开始时间
     */
    private Date beginPubDate;
    /**
     * 结束时间
     */
    private Date endPubDate;
    /**
     * 所属频道ID
     */
    private Integer channelId;
    /**
     * 关键字
     */
    private String keyword;
}
