package com.heifeng.item.controller;

import com.heifeng.common.pojo.PageResult;
import com.heifeng.item.bo.SpuBo;
import com.heifeng.item.pojo.Sku;
import com.heifeng.item.pojo.Spu;
import com.heifeng.item.pojo.SpuDetail;
import com.heifeng.item.service.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
对应spu
http://api.leyou.com/api/item
 */
@Controller
@RequestMapping()
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /*
    spu/page?key=&saleable=true&page=1&rows=5
        key:要搜索的字段
        saleable:上架是true，下架是false，全部是不给参数
         props.item.id

     */
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo>> querySupsByPage(
            @RequestParam(name = "key",required = false) String key,
            @RequestParam(name = "saleable",required = false) String saleable,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "rows") Integer pageSize){
        PageResult<SpuBo> pageResult = null;
        //不传参数是即给null
        if(StringUtils.isBlank(saleable))
            pageResult =  goodsService.querySupsByPage(key,"null",page,pageSize);
        else
            pageResult = goodsService.querySupsByPage(key,saleable,page,pageSize);
        if(CollectionUtils.isEmpty(pageResult.getItems()))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pageResult);
    }
    /*
        根据spuid查询spu的细节表
     */
    @GetMapping("spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId")Long spuId){
        SpuDetail spuDetail = goodsService.querySpuDetailBySpuId(spuId);
        if(spuDetail == null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(spuDetail);
    }

    /**
     * 根据spuid查询sku的集合
     * @param spuId
     * @return
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkusBySpuId(@RequestParam("id")Long spuId){
        List<Sku> skus = goodsService.querySkusBySpuId(spuId);
        if(CollectionUtils.isEmpty(skus)){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(skus);
    }

    @PostMapping("goods")
    public ResponseEntity<Void> saveSups(@RequestBody SpuBo spuBo){
        goodsService.saveSups(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据spuid查询spu
     * @param id
     * @return
     */
    @GetMapping("spu/{id}")
    public ResponseEntity<Spu> querySpuById(@PathVariable("id") Long id){
        Spu spu = this.goodsService.querySpuById(id);
        if(spu == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(spu);
    }
}
