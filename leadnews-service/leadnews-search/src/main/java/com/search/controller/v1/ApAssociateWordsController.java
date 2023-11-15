package com.search.controller.v1;

import com.model.common.dtos.ResponseResult;
import com.model.search.dto.UserSearchDto;
import com.search.service.ApAssociateWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ApAssociateWordsController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/15 15:28
 * {@code @Version}  1.0
 */

@RestController
@RequestMapping("/api/v1/associate")
public class ApAssociateWordsController {

    @Autowired
    private ApAssociateWordsService apAssociateWordsService;

    @PostMapping("/search")
    public ResponseResult search(@RequestBody UserSearchDto userSearchDto) {
        return apAssociateWordsService.findAssociate(userSearchDto);
    }
}
