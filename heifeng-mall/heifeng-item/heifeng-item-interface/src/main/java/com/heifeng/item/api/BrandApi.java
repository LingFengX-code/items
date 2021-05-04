package com.heifeng.item.api;

import com.heifeng.common.pojo.PageResult;
import com.heifeng.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequestMapping("brand")
public interface BrandApi {
    /*
   /page?key=&page=1&rows=5&sortBy=id&desc=false
   - page：当前页，int
   - rows：每页大小，int
   - sortBy：排序字段，String
   - desc：是否为降序，boolean
   - key：搜索关键词，String
    */
    @GetMapping("/page")
    public PageResult<Brand> queryAll(
            @RequestParam(name = "key",required = false) String key,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "rows",defaultValue = "5") Integer rows,
            @RequestParam(name = "sortBy",required = false) String sortBy,
            @RequestParam(name = "desc",required = false) Boolean desc
    );
    /*
   根据商品分类id查询对应品牌(分类表和品牌表是多对多的关系)
       /cid/76
    */
    @GetMapping("/cid/{cid}")
    public List<Brand> queryBrandsByCid(@PathVariable(name = "cid") Long cid);
    /**
     * 根据品牌id查询品牌
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Brand queryBrandById(@PathVariable("id")Long id);
}
