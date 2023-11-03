package com.article.controller.v1;

import com.article.service.ApArticleService;
import com.common.constans.ArticleConstants;
import com.model.article.dto.ArticleHomeDto;
import com.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: ArticleHomeController
 * Description:
 * |          | **加载首页**         | **加载更多**             | **加载最新**            |
 * | -------- | -------------------- | ------------------------ | ----------------------- |
 * | 接口路径 | /api/v1/article/load | /api/v1/article/loadmore | /api/v1/article/loadnew |
 * | 请求方式 | POST                 | POST                     | POST                    |
 * | 参数     | ArticleHomeDto       | ArticleHomeDto           | ArticleHomeDto          |
 * | 响应结果 | ResponseResult       | ResponseResult           | ResponseResult          |
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/3 14:52
 * {@code @Version}  1.0
 */

@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController {

    @Resource
    private ApArticleService apArticleService;

    @PostMapping("/load")
    public ResponseResult load(@RequestBody ArticleHomeDto articleHomeDto){
        return apArticleService.load(ArticleConstants.LOADTYPE_LOAD_MORE,articleHomeDto);
    }

    @PostMapping("/loadmore")
    public ResponseResult loadmore(@RequestBody ArticleHomeDto articleHomeDto){
        return apArticleService.load(ArticleConstants.LOADTYPE_LOAD_MORE,articleHomeDto);
    }

    @PostMapping("/loadnew")
    public ResponseResult loadnew(@RequestBody ArticleHomeDto articleHomeDto){
        return apArticleService.load(ArticleConstants.LOADTYPE_LOAD_NEW,articleHomeDto);
    }


}
