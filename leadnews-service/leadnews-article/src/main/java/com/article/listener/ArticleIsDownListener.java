package com.article.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.StringUtils;
import com.article.service.ApArticleConfigService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.model.article.pojo.ApArticleConfig;
import com.model.common.constants.WmNewsMessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.sql.Wrapper;
import java.util.Map;

/**
 * ClassName: ArtilceIsDownListener
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/13 15:48
 * {@code @Version}  1.0
 */
@Component
@Slf4j
public class ArticleIsDownListener {
    @Autowired
    private ApArticleConfigService apArticleConfigService;

    @KafkaListener(topics = WmNewsMessageConstants.WM_NEWS_UP_OR_DOWN_TOPIC)
    public void upOrDownMessage(String message){
        if(StringUtils.isNotBlank(message)){
            Map map = JSON.parseObject(message, Map.class);
            apArticleConfigService.updateByMap(map);
            log.info("article端文章配置上架修改，articleId={}",map.get("articleId"));
        }
    }

    @KafkaListener(topics = WmNewsMessageConstants.WM_NEWS_DELETE_TOPIC)
    public void deleteMessage(String message){
        if(StringUtils.isNotBlank(message)){
            Map map = JSON.parseObject(message, Map.class);
            apArticleConfigService.update(Wrappers.<ApArticleConfig>lambdaUpdate().eq(ApArticleConfig::getArticleId,map.get("articleId"))
                    .set(ApArticleConfig::getIsDelete,true));
            log.info("article端文章配置删除修改，articleId={}",map.get("articleId"));
        }
    }
}
