package com.search.controller.v1;

import com.alibaba.fastjson.JSON;
import com.model.common.dtos.ResponseResult;
import com.model.search.dto.HistorySearchDto;
import com.search.service.ApUserSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: ApUserSearchController
 * Description:
 * {@code @Author} 苏羽晨
 * {@code @Create} 2023/11/15 15:19
 * {@code @Version}  1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/history")
public class ApUserSearchController {

    @Autowired
    private ApUserSearchService apUserSearchService;

    @PostMapping("/load")
    public ResponseResult findUserSearch() {
        return apUserSearchService.findUserSearch();
    }

    @PostMapping("/del")
    public ResponseResult delUserSearch(@RequestBody String dto) {
        Map<String,String> map = JSON.parseObject(dto, Map.class);
        String id = map.get("id");
        return apUserSearchService.delUserSearch(id);
    }
}
