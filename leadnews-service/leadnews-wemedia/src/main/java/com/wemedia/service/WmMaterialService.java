package com.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.common.dtos.ResponseResult;
import com.model.wemedia.dtos.WmMaterialDto;
import com.model.wemedia.pojos.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: WmMaterialService
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/6 16:00
 * {@code @Version}  1.0
 */
public interface WmMaterialService extends IService<WmMaterial> {

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    public ResponseResult uploadPicture(MultipartFile multipartFile);

    /**
     * 素材列表查询
     * @param dto
     * @return
     */
    public ResponseResult findList( WmMaterialDto dto);

    /**
     * 删除id对应素材
     * @param id
     * @return
     */
    ResponseResult deletePicture(Long id);

    /**
     * 收藏或取消收藏id对应素材
     * @param id
     * @return
     */
    ResponseResult collectPicture(Long id,short val);
}
