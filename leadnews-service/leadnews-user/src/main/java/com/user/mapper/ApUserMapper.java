package com.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.user.pojo.ApUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ApUserMapper
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/2 12:04
 * {@code @Version}  1.0
 */
@Mapper
public interface ApUserMapper extends BaseMapper<ApUser> {
}
