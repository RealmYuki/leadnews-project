package com.search.service;

import com.model.common.dtos.ResponseResult;
import com.model.search.dto.HistorySearchDto;

/**
 * ClassName: ApUserSearchService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/15 14:42
 * {@code @Version}  1.0
 */
public interface ApUserSearchService {

    /**
     * 保存用户搜索历史记录
     * @param keyword
     * @param userId
     */
    public void insert(String keyword,Integer userId);

    /**
     查询搜索历史
     @return
     */
    public ResponseResult findUserSearch();

    /**
     删除搜索历史
     @param id
     @return
     */
    ResponseResult delUserSearch(String id);
}