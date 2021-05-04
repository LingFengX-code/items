package com.heifeng.item.controller;

import com.heifeng.item.pojo.Category;
import com.heifeng.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/show")
    @ResponseBody
    public List<Category> test(){
        return categoryService.findAll();
    }

    /*
    pid:category的父结点id
    http://api.heifeng.com/api/item/category/list?pid=0
     */
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryByPid(@RequestParam(name = "pid",defaultValue = "0")Long pid) {
        List<Category> categoryList = null;
        try {
            if (pid==null || pid<0) {
                //400错误
                return ResponseEntity.badRequest().build();
            }
            categoryList = categoryService.queryByPid(pid);
            if(CollectionUtils.isEmpty(categoryList)){
                //404错误
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //200成功
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids")List<Long> ids){
        List<String> categoryNames = categoryService.queryCategoryNamesByCids(ids);
        if(CollectionUtils.isEmpty(categoryNames)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryNames);
    }
}
