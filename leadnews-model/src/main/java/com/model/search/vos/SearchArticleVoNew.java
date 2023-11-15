package com.model.search.vos;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class SearchArticleVoNew {

    // 文章id
    private Long id;
    // 文章标题
    private String title;
    // 文章发布时间
    private Date publishTime;
    // 文章布局
    private Integer layout;
    // 封面
    private String images;
    // 作者id
    private Long authorId;
    // 作者名词
    private String authorName;
    //静态url
    private String staticUrl;
    //文章内容
    private String content;

    //文章内容纯文本
    private String contentText;

    public void handelText() {
        //存储纯文本内容
        StringBuilder stringBuilder = new StringBuilder();

        //提取文本
        if(StringUtils.isNotBlank(this.getContent())){
            List<Map> maps = JSONArray.parseArray(this.getContent(), Map.class);
            for (Map map : maps) {
                if (map.get("type").equals("text")){
                    stringBuilder.append(map.get("value"));
                }
            }
        }
        this.contentText = stringBuilder.toString();
    }


}