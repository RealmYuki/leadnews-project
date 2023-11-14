package com.search.controller.v1;

import com.model.common.dtos.ResponseResult;
import com.model.search.dto.UserSearchDto;
import com.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ArticleSearchController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/14 14:59
 * {@code @Version}  1.0
 */

@RestController
@RequestMapping("/api/v1/article/search")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    @PostMapping("/search")
    public ResponseResult search(@RequestBody UserSearchDto userSearchDto){
        return articleSearchService.search(userSearchDto);
    }

}
