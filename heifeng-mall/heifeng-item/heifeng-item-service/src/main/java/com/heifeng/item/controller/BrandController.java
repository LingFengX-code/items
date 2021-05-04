package com.heifeng.item.controller;

import com.heifeng.common.pojo.PageResult;
import com.heifeng.item.pojo.Brand;
import com.heifeng.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
http://api.leyou.com/api/item/brand
 */
@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /*
    /page?key=&page=1&rows=5&sortBy=id&desc=false
    - page：当前页，int
    - rows：每页大小，int
    - sortBy：排序字段，String
    - desc：是否为降序，boolean
    - key：搜索关键词，String
     */
    @GetMapping("/page")
    public ResponseEntity<PageResult<Brand>> queryAll(
            @RequestParam(name = "key",required = false) String key,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "rows",defaultValue = "5") Integer rows,
            @RequestParam(name = "sortBy",required = false) String sortBy,
            @RequestParam(name = "desc",required = false) Boolean desc
    ){
        PageResult<Brand> pageResult = null;
        pageResult = brandService.queryAll(key,page,rows,sortBy,desc);
        if(CollectionUtils.isEmpty(pageResult.getItems())){
            //404
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }

    /*
    根据商品分类id查询对应品牌(分类表和品牌表是多对多的关系)
        /cid/76
     */
    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandsByCid(@PathVariable(name = "cid") Long cid){
        if(cid==null || cid<0) return ResponseEntity.badRequest().build();
        List<Brand> brands = brandService.queryBrandsByCid(cid);
        if(CollectionUtils.isEmpty(brands)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(brands);
    }

    /**
     * 根据品牌id查询品牌
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Brand> queryBrandById(@PathVariable("id")Long id){
        Brand brand = brandService.queryBrandById(id);
        if(brand==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brand);
    }
    /*
    新增商品
    {name: "dd", image: "", cids: "74,1", letter: "D"}
    brand:此类的属性
        name:
        image:
        letter:
    cids:传入一个分类的数组
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand,@RequestParam("cids") List<Long> cids){
       if(brandService.saveBrand(brand,cids)) return ResponseEntity.status(HttpStatus.CREATED).build();
       //服务器错误
       return ResponseEntity.status(500).build();
    }
}
