package com.search.service;

import com.model.common.dtos.ResponseResult;
import com.model.search.dto.UserSearchDto;

/**
 * ClassName: ApAssociateWordsService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/15 15:29
 * {@code @Version}  1.0
 */

/**
 * <p>
 * 联想词表 服务类
 * </p>
 *
 * @author itheima
 */
public interface ApAssociateWordsService {

    /**
     联想词
     @param userSearchDto
     @return
     */
    ResponseResult findAssociate(UserSearchDto userSearchDto);

}