package com.heifeng.item.api;

import com.heifeng.item.pojo.SpecGroup;
import com.heifeng.item.pojo.SpecParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequestMapping("spec")
public interface SpecificationApi {
    /*
    /groups/76
     */
    @GetMapping("/groups/{cid}")
    public List<SpecGroup> querySpecGroupsByCid(@PathVariable(name = "cid") Long cid);
    /*
  /params?gid=1
  gid:规格参数组id
  cid:分类id
  注：cid与gid不同时进行查询
   */
    @GetMapping("/params")
    public List<SpecParam> querySpecParamsByGid(
            @RequestParam(name = "gid",defaultValue = "-1") Long gid,
            @RequestParam(name = "cid",defaultValue = "-1") Long cid,
            @RequestParam(name = "generic",required = false)Boolean generic,
            @RequestParam(name = "searching",required = false)Boolean searching);

    // 查询规格参数组，及组内参数
    @GetMapping("{cid}")
    List<SpecGroup> querySpecsByCid(@PathVariable("cid") Long cid);
}
