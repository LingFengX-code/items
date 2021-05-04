package com.heifeng.item.controller;

import com.heifeng.item.pojo.SpecGroup;
import com.heifeng.item.service.SpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
http://api.leyou.com/api/item/spec
 */
@Controller
@RequestMapping("spec")
public class SpecGroupController {
    @Autowired
    private SpecGroupService specGroupService;

    /*
    /groups/76
     */
    @GetMapping("/groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroupsByCid(@PathVariable(name = "cid") Long cid){
        List<SpecGroup> specGroups = specGroupService.querySpecGroupsByCid(cid);
        if(cid==null || cid<0) return ResponseEntity.badRequest().build();
        if(CollectionUtils.isEmpty(specGroups)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(specGroups);
    }

    /**
     * 根据分类id查找指定的规格参数组
     * @param cid
     * @return
     */
    @GetMapping("{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecsByCid(@PathVariable("cid") Long cid){
        List<SpecGroup> list = this.specGroupService.querySpecsByCid(cid);
        if(list == null || list.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
