package com.apis.article;

import com.apis.article.fallback.IArticleClientFallback;
import com.model.article.dto.ArticleDto;
import com.model.article.pojo.ApArticle;
import com.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: IArticleClient
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/8 11:11
 * {@code @Version}  1.0
 */
@FeignClient(value = "leadnews-article",fallback = IArticleClientFallback.class)
public interface IArticleClient {
    @PostMapping("/api/v1/article/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto dto) ;

    @GetMapping("/api/v1/article/get/{id}")
    public ApArticle getArticle(@PathVariable Long id);
}
