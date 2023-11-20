package com.article.controller.v1;

import com.article.service.ApCollectionService;
import com.model.article.dto.CollectionBehaviorDto;
import com.model.behavior.dtos.UnlikeBehaviorDto;
import com.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * ClassName: ArticleCollectionController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 16:45
 * {@code @Version}  1.0
 */


@RestController
@RequestMapping("/api/v1")
public class ArticleCollectionController {
    @Autowired
    private ApCollectionService apCollectionService;

    @PostMapping("/collection_behavior")
    public ResponseResult collectionBehavior(@RequestBody CollectionBehaviorDto dto){
        return apCollectionService.collection(dto);
    }
}
