package com.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.article.pojo.ApArticleConfig;

import java.util.Map;

/**
 * ClassName: ApArticleConfigService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/13 15:48
 * {@code @Version}  1.0
 */
public interface ApArticleConfigService extends IService<ApArticleConfig> {
    /**
     * 修改文章配置
     * @param map
     */
    public void updateByMap(Map map);

}
