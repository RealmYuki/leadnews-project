package com.article.feign;

import com.apis.article.IArticleClient;
import com.article.service.ApArticleService;
import com.model.article.dto.ArticleDto;
import com.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ArticleClient
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/8 11:13
 * {@code @Version}  1.0
 */

@RestController
public class ArticleClient implements IArticleClient {

    @Autowired
    private ApArticleService apArticleService;

    @Override
    public ResponseResult saveArticle(ArticleDto dto) {
        return apArticleService.saveArticle(dto);
    }
}
