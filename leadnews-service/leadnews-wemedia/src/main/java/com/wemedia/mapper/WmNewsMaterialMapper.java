package com.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.wemedia.pojos.WmNewsMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: WmNewsMaterialMapper
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/7 15:10
 * {@code @Version}  1.0
 */
@Mapper
public interface WmNewsMaterialMapper extends BaseMapper<WmNewsMaterial> {

    /**
     * 向数据库中保存多个素材与文章的关系
     * @param materialIds
     * @param newsId
     * @param type
     */
    void saveRelations(@Param("materialIds") List<Integer> materialIds, @Param("newsId") Integer newsId, @Param("type")Short type);
}
