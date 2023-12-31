package com.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.StringUtils;
import com.apis.article.IArticleClient;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.exception.CustomException;
import com.model.article.pojo.ApArticle;
import com.model.common.constants.ArticleConstants;
import com.model.common.constants.WemediaConstants;
import com.model.common.constants.WmNewsMessageConstants;
import com.model.common.dtos.PageResponseResult;
import com.model.common.dtos.ResponseResult;
import com.model.common.enums.AppHttpCodeEnum;
import com.model.search.vos.SearchArticleVoNew;
import com.model.wemedia.dtos.NewsAuthDto;
import com.model.wemedia.dtos.WmNewsDto;
import com.model.wemedia.dtos.WmNewsPageReqDto;
import com.model.wemedia.dtos.WmNewsUpOrDownDto;
import com.model.wemedia.pojos.WmMaterial;
import com.model.wemedia.pojos.WmNews;
import com.model.wemedia.pojos.WmNewsMaterial;
import com.model.wemedia.pojos.WmUser;
import com.utils.thread.WmThreadLocalUtil;
import com.wemedia.mapper.WmMaterialMapper;
import com.wemedia.mapper.WmNewsMapper;
import com.wemedia.mapper.WmNewsMaterialMapper;
import com.wemedia.mapper.WmUserMapper;
import com.wemedia.service.WmNewsAutoScanService;
import com.wemedia.service.WmNewsService;
import com.wemedia.service.WmUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.model.common.constants.WmNewsMessageConstants.WM_NEWS_ES_DEL_SYNC_TOPIC;

/**
 * ClassName: WmNewsServiceImpl
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/7 14:45
 * {@code @Version}  1.0
 */

@Transactional
@Slf4j
@Service
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {
    @Override
    public ResponseResult findAll(WmNewsPageReqDto dto) {
        //检查参数
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam();
        //获取登陆人信息
        WmUser wmUser = WmThreadLocalUtil.getUser();
        if (wmUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //状态精确查询
        if (dto.getStatus() != null) {
            lambdaQueryWrapper.eq(WmNews::getStatus, dto.getStatus());
        }
        //频道精确查询
        if (dto.getChannelId() != null) {
            lambdaQueryWrapper.eq(WmNews::getChannelId, dto.getChannelId());
        }
        //时间范围查询
        if (dto.getBeginPubDate() != null && dto.getEndPubDate() != null) {
            lambdaQueryWrapper.between(WmNews::getPublishTime, dto.getBeginPubDate(), dto.getEndPubDate());
        }
        //关键字模糊查询
        if (dto.getKeyword() != null) {
            lambdaQueryWrapper.like(WmNews::getTitle, dto.getKeyword());
        }
        //查询当前登录用户的文章
        lambdaQueryWrapper.eq(WmNews::getUserId, wmUser.getId());
        //发布时间倒序查询
        lambdaQueryWrapper.orderByDesc(WmNews::getCreatedTime);
        page = page(page, lambdaQueryWrapper);
        PageResponseResult pageResponseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        pageResponseResult.setData(page.getRecords());
        return pageResponseResult;
    }

    @Autowired
    private WmNewsAutoScanService wmNewsAutoScanService;

    @Override
    public ResponseResult submitNews(WmNewsDto dto) {
        //条件判断
        if (dto == null || dto.getContent() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //文章创建
        WmNews wmNews = attributeCompletion(dto);
        //保存至数据库
        saveOrUpdateWmNews(wmNews);
        //判断是否为草稿，是草稿直接返回
        if (dto.getStatus().equals(WmNews.Status.NORMAL.getCode())) {
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        //不是草稿，保存文章内容图片与素材的关系
        //获取到文章内容中的图片信息
        List<String> materialUrls = extractUrlInfo(dto.getContent());
        saveRelativeInfoForContent(materialUrls, wmNews.getId());
        //保存文章封面图片与素材的关系，如果当前布局是自动，需要匹配封面图片
        saveRelativeInfoForCover(dto, wmNews, materialUrls);
        //提交审核(异步执行)
        wmNewsAutoScanService.autoScanWmNews(wmNews.getId());
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult findOne(Long id) {
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmNews wmNews = getById(id);
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        return ResponseResult.okResult(wmNews);
    }

    @Override
    public ResponseResult deleteNews(Long id) {
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmNews wmNews = getById(id);
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        if (wmNews.getStatus().equals(WmNews.Status.NORMAL.getCode())) {
            return ResponseResult.okResult(removeById(id));
        }
        wmNewsMaterialMapper.delete(new LambdaQueryWrapper<WmNewsMaterial>().eq(WmNewsMaterial::getNewsId, id));
        if (wmNews.getStatus().equals(WmNews.Status.PUBLISHED.getCode())) {
            if (wmNews.getArticleId() != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("articleId", wmNews.getArticleId());
                kafkaTemplate.send(WmNewsMessageConstants.WM_NEWS_DELETE_TOPIC, JSON.toJSONString(map));
            }
        }
        return ResponseResult.okResult(removeById(id));
    }

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Qualifier("com.apis.article.IArticleClient")
    @Autowired
    private IArticleClient articleClient;


    @Override
    public ResponseResult downOrUp(WmNewsUpOrDownDto dto) {
        //1.检查参数
        if (dto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.查询文章
        WmNews wmNews = getById(dto.getId());
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "文章不存在");
        }

        //3.判断文章是否已发布
        if (!wmNews.getStatus().equals(WmNews.Status.PUBLISHED.getCode())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "当前文章不是发布状态，不能上下架");
        }

        //4.判断文章enable参数
        if (dto.getEnable() == null && dto.getEnable() < 0 && dto.getEnable() > 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        wmNews.setEnable(dto.getEnable());
        updateById(wmNews);
        if (wmNews.getArticleId() != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("articleId", wmNews.getArticleId());
            map.put("enable", dto.getEnable());
            kafkaTemplate.send(WmNewsMessageConstants.WM_NEWS_UP_OR_DOWN_TOPIC, JSON.toJSONString(map));
            if (dto.getEnable() == 0) {
                kafkaTemplate.send(WM_NEWS_ES_DEL_SYNC_TOPIC, JSON.toJSONString(map));
            } else if (dto.getEnable() == 1) {
                SearchArticleVoNew vo = new SearchArticleVoNew();
                ApArticle apArticle = articleClient.getArticle(wmNews.getArticleId());
                BeanUtils.copyProperties(apArticle, vo);
                vo.setContent(wmNews.getContent());
                vo.setLayout(Integer.valueOf(wmNews.getType()));
                vo.handelText();
                kafkaTemplate.send(ArticleConstants.ARTICLE_ES_INS_SYNC_TOPIC, JSON.toJSONString(vo));
            }
        }

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Autowired
    private WmUserService wmUserService;

    @Override
    public ResponseResult adminList(NewsAuthDto dto) {
        //检查参数
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam();
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(dto.getTitle() != null ){
            lambdaQueryWrapper.like(WmNews::getTitle,dto.getTitle());
        }
        if (dto.getStatus() != null){
            lambdaQueryWrapper.eq(WmNews::getStatus,dto.getStatus());
        }
        lambdaQueryWrapper.orderByDesc(WmNews::getCreatedTime);
        page = page(page,lambdaQueryWrapper);
        List<WmNews> wmNewsList = page.getRecords();
        if (wmNewsList.size()!=0){
            LambdaQueryWrapper<WmUser> userQueryWrapper = new LambdaQueryWrapper<>();
            for (WmNews wmNews : wmNewsList){
                userQueryWrapper.eq(WmUser::getId,wmNews.getUserId());
                userQueryWrapper.select(WmUser::getName);
                wmNews.setAuthorName(wmUserService.getOne(userQueryWrapper).getName());
            }
        }
        PageResponseResult pageResponseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        pageResponseResult.setData(wmNewsList);
        return pageResponseResult;
    }

    @Override
    public ResponseResult adminFindOne(Long id) {
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(WmNews::getArticleId,id);
        WmNews wmNews = getById(id);
        if (wmNews == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        LambdaQueryWrapper<WmUser> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(WmUser::getId,wmNews.getUserId());
        userQueryWrapper.select(WmUser::getName);
        wmNews.setAuthorName(wmUserService.getOne(userQueryWrapper).getName());
        return ResponseResult.okResult(wmNews);
    }


    @Override
    public ResponseResult adminAuthFail(NewsAuthDto dto) {
        if (dto.getId() == null||dto.getMsg() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        WmNews wmNews = getById(dto.getId());
        if (wmNews==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        wmNews.setReason(dto.getMsg());
        wmNews.setStatus((short) 2);
        return ResponseResult.okResult(updateById(wmNews));
    }

    @Override
    public ResponseResult adminAuthPass(NewsAuthDto dto) {
        if (dto.getId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        WmNews wmNews = getById(dto.getId());
        if (wmNews==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        ResponseResult responseResult = wmNewsAutoScanService.saveAppArticle(wmNews);
        if(!responseResult.getCode().equals(200)){
            throw new RuntimeException("WmNewsAutoScanServiceImpl-文章审核，保存app端相关文章数据失败");
        }
        //回填article_id
        wmNews.setArticleId((Long) responseResult.getData());
        wmNews.setStatus((short) 9);
        wmNews.setReason("审核通过");
        updateById(wmNews);
        return ResponseResult.okResult(responseResult);
    }

    /**
     * 第一个功能：如果当前封面类型为自动，则设置封面类型的数据
     * 匹配规则：
     * 1，如果内容图片大于等于1，小于3  单图  type 1
     * 2，如果内容图片大于等于3  多图  type 3
     * 3，如果内容没有图片，无图  type 0
     * 第二个功能：保存封面图片与素材的关系
     *
     * @param dto
     * @param wmNews
     * @param materialUrls
     */
    private void saveRelativeInfoForCover(WmNewsDto dto, WmNews wmNews, List<String> materialUrls) {
        List<String> images = dto.getImages();
        //如果当前封面类型为自动，则设置封面类型的数据
        if (dto.getType().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)) {
            //多图
            if (materialUrls.size() >= 3) {
                wmNews.setType(WemediaConstants.WM_NEWS_MANY_IMAGE);
                images = materialUrls.stream().limit(3).collect(Collectors.toList());
            } else if (materialUrls.size() >= 1 && materialUrls.size() < 3) {
                //单图
                wmNews.setType(WemediaConstants.WM_NEWS_SINGLE_IMAGE);
                images = materialUrls.stream().limit(1).collect(Collectors.toList());
            } else {
                //无图
                wmNews.setType(WemediaConstants.WM_NEWS_NONE_IMAGE);
            }

            //修改文章
            if (images != null && images.size() > 0) {
                wmNews.setImages(StringUtils.join(images, ","));
            }
            updateById(wmNews);
        }
        if (images != null && images.size() > 0) {
            saveRelativeInfo(images, wmNews.getId(), WemediaConstants.WM_COVER_REFERENCE);
        }
    }

    /**
     * 处理文章内容图片与素材的关系
     *
     * @param materialUrls
     * @param id
     */
    private void saveRelativeInfoForContent(List<String> materialUrls, Integer id) {
        saveRelativeInfo(materialUrls, id, WemediaConstants.WM_CONTENT_REFERENCE);
    }

    /**
     * 保存文章图片与素材的关系到数据库中
     *
     * @param materials
     * @param newsId
     * @param type
     */
    private void saveRelativeInfo(List<String> materials, Integer newsId, Short type) {
        if (materials != null && !materials.isEmpty()) {
            //通过图片的url查询素材的id
            List<WmMaterial> dbMaterials = wmMaterialMapper.selectList(Wrappers.<WmMaterial>lambdaQuery().in(WmMaterial::getUrl, materials));

            //判断素材是否有效
            if (dbMaterials == null || dbMaterials.size() == 0) {
                //手动抛出异常   第一个功能：能够提示调用者素材失效了，第二个功能，进行数据的回滚
                throw new CustomException(AppHttpCodeEnum.MATERIALS_REFERENCE_FAIL);
            }

            if (materials.size() != dbMaterials.size()) {
                throw new CustomException(AppHttpCodeEnum.MATERIALS_REFERENCE_FAIL);
            }

            List<Integer> idList = dbMaterials.stream().map(WmMaterial::getId).collect(Collectors.toList());

            //批量保存
            wmNewsMaterialMapper.saveRelations(idList, newsId, type);
        }

    }

    /**
     * 提取文章内容中的图片信息
     *
     * @param content
     * @return
     */
    private List<String> extractUrlInfo(String content) {
        ArrayList<String> materialUrls = new ArrayList<>();
        List<Map> maps = JSON.parseArray(content, Map.class);
        for (Map map : maps) {
            if (map.get("type").equals("image")) {
                String imageUrl = (String) map.get("value");
                materialUrls.add(imageUrl);
            }
        }
        return materialUrls;
    }

    /**
     * 将前端提交的文章信息对应到实体中
     *
     * @param dto
     * @return
     */
    private static WmNews attributeCompletion(WmNewsDto dto) {
        WmNews wmNews = new WmNews();
        BeanUtils.copyProperties(dto, wmNews);
        //封面图片  list---> string
        if (dto.getImages() != null && dto.getImages().size() > 0) {
            String imageStr = StringUtils.join(dto.getImages(), ",");
            wmNews.setImages(imageStr);
        }
        //如果当前封面类型为自动 -1在数据库中不符 先设置为null
        if (dto.getType().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)) {
            wmNews.setType(null);
        }
        wmNews.setUserId(WmThreadLocalUtil.getUser().getId());
        wmNews.setCreatedTime(new Date());
        wmNews.setSubmitedTime(new Date());
        //默认上架
        wmNews.setEnable((short) 1);
        return wmNews;
    }

    @Autowired
    private WmNewsMaterialMapper wmNewsMaterialMapper;

    @Autowired
    private WmMaterialMapper wmMaterialMapper;

    /**
     * 保存或修改文章到数据库
     *
     * @param wmNews
     */
    private void saveOrUpdateWmNews(WmNews wmNews) {
        if (wmNews.getId() == null) {
            //保存
            save(wmNews);
        } else {
            //修改
            //删除文章图片与素材的关系
            wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId, wmNews.getId()));
            updateById(wmNews);
        }
    }
}
