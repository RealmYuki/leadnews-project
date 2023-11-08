package com.wemedia.service.impl;

import com.apis.article.IArticleClient;
import com.model.article.dto.ArticleDto;
import com.model.common.dtos.ResponseResult;
import com.model.wemedia.pojos.WmChannel;
import com.model.wemedia.pojos.WmNews;
import com.model.wemedia.pojos.WmUser;
import com.wemedia.mapper.WmChannelMapper;
import com.wemedia.mapper.WmNewsMapper;
import com.wemedia.mapper.WmUserMapper;
import com.wemedia.service.WmNewsAutoScanService;
import com.wemedia.service.WmUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * ClassName: WmNewsAutoScanServiceImpl
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/8 13:58
 * {@code @Version}  1.0
 */

@Service
@Slf4j
@Transactional
public class WmNewsAutoScanServiceImpl implements WmNewsAutoScanService {

    @Autowired
    private WmNewsMapper wmNewsMapper;

    @Async
    @Override
    public void autoScanWmNews(Integer id) {
        //通过id查找WmNews
        WmNews wmNews = wmNewsMapper.selectById(id);
        if(wmNews == null){
            throw new RuntimeException("WmNewsAutoScanServiceImpl-文章不存在");
        }
        //检查文章状态，若为刚提交待审核，则开始审核
        if(wmNews.getStatus().equals(WmNews.Status.SUBMIT.getCode())){
            //审核通过将文章保存至App端的文章数据库
            ResponseResult responseResult = saveAppArticle(wmNews);
            if(!responseResult.getCode().equals(200)){
                throw new RuntimeException("WmNewsAutoScanServiceImpl-文章审核，保存app端相关文章数据失败");
            }
            //回填article_id
            wmNews.setArticleId((Long) responseResult.getData());
            wmNews.setStatus((short) 9);
            wmNews.setReason("审核成功");
            wmNewsMapper.updateById(wmNews);
        }

    }

    @Autowired
    private WmChannelMapper wmChannelMapper;

    @Autowired
    private WmUserMapper wmUserMapper;

    @Autowired
    private IArticleClient articleClient;

    /**
     * 审核通过将文章保存至App端的文章数据库
     * @param wmNews
     * @return
     */
    private ResponseResult saveAppArticle(WmNews wmNews) {
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(wmNews,articleDto);
        articleDto.setLayout(wmNews.getType());
        //频道
        WmChannel wmChannel = wmChannelMapper.selectById(wmNews.getChannelId());
        if (wmChannel != null){
            articleDto.setChannelName(wmChannel.getName());
        }
        //作者
        articleDto.setAuthorId(wmNews.getUserId().longValue());
        WmUser wmUser = wmUserMapper.selectById(wmNews.getUserId());
        if(wmUser != null){
            articleDto.setAuthorName(wmUser.getName());
        }

        //设置文章id
        if(wmNews.getArticleId() != null){
            articleDto.setId(wmNews.getArticleId());
        }
        articleDto.setCreatedTime(new Date());
        ResponseResult responseResult = articleClient.saveArticle(articleDto);
        return responseResult;
    }
}
