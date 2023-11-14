package com.search.service;

import com.model.common.dtos.ResponseResult;
import com.model.search.dto.UserSearchDto;

import java.io.IOException;

/**
 * ClassName: ApArticleSearchService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/14 15:02
 * {@code @Version}  1.0
 */
public interface ArticleSearchService {
    /**
     ES文章分页搜索
     @return
     */
    ResponseResult search(UserSearchDto dto);
}
