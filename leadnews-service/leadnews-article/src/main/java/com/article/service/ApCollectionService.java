package com.article.service;

import com.model.article.dto.CollectionBehaviorDto;
import com.model.behavior.dtos.UnlikeBehaviorDto;
import com.model.common.dtos.ResponseResult;

/**
 * ClassName: ApCollectionService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 16:47
 * {@code @Version}  1.0
 */
public interface ApCollectionService {


    ResponseResult collection(CollectionBehaviorDto dto);
}
