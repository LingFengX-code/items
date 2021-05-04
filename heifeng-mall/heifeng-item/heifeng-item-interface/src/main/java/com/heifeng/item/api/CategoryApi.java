package com.heifeng.item.api;

import com.heifeng.item.pojo.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequestMapping("category")
public interface CategoryApi {
    /*
   pid:category的父结点id
   http://api.leyou.com/api/item/category/list?pid=0
    */
    @GetMapping("/list")
    public List<Category> queryByPid(@RequestParam(name = "pid",defaultValue = "0")Long pid);
    @GetMapping("/names")
    public List<String> queryNamesByIds(@RequestParam("ids")List<Long> ids);
}
