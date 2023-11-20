package com.article.controller.v1;

import com.article.service.ApArticleService;
import com.model.article.dto.ArticleInfoDto;
import com.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ArticleInfoController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/20 16:59
 * {@code @Version}  1.0
 */
@RestController
@RequestMapping("/api/v1/article")
public class ArticleInfoController {

    @Autowired
    private ApArticleService apArticleService;

    @PostMapping("/load_article_behavior")
    public ResponseResult loadArticleBehavior(@RequestBody ArticleInfoDto dto){
        return apArticleService.loadArticleBehavior(dto);
    }
}
