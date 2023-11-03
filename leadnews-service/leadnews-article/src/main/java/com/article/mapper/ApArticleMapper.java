package com.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.article.dto.ArticleHomeDto;
import com.model.article.pojo.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: ApArticleMapper
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/3 14:55
 * {@code @Version}  1.0
 */
@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {
    public List<ApArticle> loadArticleList(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);
}
