package com.apis.article;

import com.model.article.dto.ArticleDto;
import com.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: IArticleClient
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/8 11:11
 * {@code @Version}  1.0
 */

@FeignClient("leadnews-article")
public interface IArticleClient {
    @PostMapping("/api/v1/article/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto dto) ;
}
