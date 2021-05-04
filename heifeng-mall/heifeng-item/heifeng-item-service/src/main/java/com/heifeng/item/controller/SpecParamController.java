package com.heifeng.item.controller;

import com.heifeng.item.pojo.SpecParam;
import com.heifeng.item.service.SpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
http://api.leyou.com/api/item/spec/params?gid=1
 */
@Controller
@RequestMapping("spec")
public class SpecParamController {
    @Autowired
    private SpecParamService specParamService;
    /*
    /params?gid=1
    gid:规格参数组id
    cid:分类id
    注：cid与gid不同时进行查询
     */
    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> querySpecParamsByGid(
            @RequestParam(name = "gid",defaultValue = "-1") Long gid,
            @RequestParam(name = "cid",defaultValue = "-1") Long cid,
            @RequestParam(name = "generic",required = false)Boolean generic,
            @RequestParam(name = "searching",required = false)Boolean searching){

        if(gid!=null && gid>0){
            List<SpecParam> specParams = specParamService.querySpecParamsByGid(gid,generic,searching);
            if(CollectionUtils.isEmpty(specParams)) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(specParams);
        }
        if(cid !=null && cid>0){
            List<SpecParam> specParams = specParamService.querySpecParamsByCid(cid,generic,searching);
            if(CollectionUtils.isEmpty(specParams)) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(specParams);
        }
        return ResponseEntity.badRequest().build();
    }

}
