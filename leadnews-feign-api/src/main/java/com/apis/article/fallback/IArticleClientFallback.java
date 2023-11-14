package com.apis.article.fallback;

/**
 * ClassName: IArticleClientFallback
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/8 14:50
 * {@code @Version}  1.0
 */

import com.apis.article.IArticleClient;
import com.model.article.dto.ArticleDto;
import com.model.article.pojo.ApArticle;
import com.model.common.dtos.ResponseResult;
import com.model.common.enums.AppHttpCodeEnum;
import org.springframework.stereotype.Component;

/**
 * feign失败配置
 * @author itheima
 */
@Component
public class IArticleClientFallback implements IArticleClient {
    @Override
    public ResponseResult saveArticle(ArticleDto dto)  {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"获取数据失败");
    }

    @Override
    public ApArticle getArticle(Long id) {
        return null;
    }
}