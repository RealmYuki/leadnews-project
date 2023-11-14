package com.search.listener;

import com.alibaba.fastjson.JSON;
import com.model.common.constants.ArticleConstants;
import com.model.common.constants.WmNewsMessageConstants;
import com.model.search.vos.SearchArticleVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * ClassName: SyncArticleListener
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/14 16:06
 * {@code @Version}  1.0
 */
@Component
@Slf4j
public class SyncArticleListener {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @KafkaListener(topics = ArticleConstants.ARTICLE_ES_INS_SYNC_TOPIC)
    public void indexMessage(String message){
        if(StringUtils.isNotBlank(message)){

            log.info("SyncArticleInsertListener,message={}",message);

            SearchArticleVo searchArticleVo = JSON.parseObject(message, SearchArticleVo.class);
            IndexRequest indexRequest = new IndexRequest("app_info_article");
            indexRequest.id(searchArticleVo.getId().toString());
            indexRequest.source(message, XContentType.JSON);
            try {
                restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("sync es error={}",e);
            }
        }
    }

    @KafkaListener(topics = WmNewsMessageConstants.WM_NEWS_ES_DEL_SYNC_TOPIC)
    public void deleteMessage(String message){
        if(StringUtils.isNotBlank(message)){
            log.info("SyncArticleDeleteListener,message={}",message);
            Map map = JSON.parseObject(message, Map.class);
            DeleteRequest deleteRequest = new DeleteRequest("app_info_article", map.get("articleId").toString());
            try {
                restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("sync es error={}",e);
            }
        }
    }

}
