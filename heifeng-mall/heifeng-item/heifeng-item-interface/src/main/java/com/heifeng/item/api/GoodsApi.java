package com.heifeng.item.api;

import com.heifeng.common.pojo.PageResult;
import com.heifeng.item.bo.SpuBo;
import com.heifeng.item.pojo.Sku;
import com.heifeng.item.pojo.Spu;
import com.heifeng.item.pojo.SpuDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
goods的对外接口
 */
@RequestMapping()
public interface GoodsApi {
    /*
       根据spuid查询spu的细节表
    */
    @GetMapping("spu/detail/{spuId}")
    public SpuDetail querySpuDetailBySpuId(@PathVariable("spuId")Long spuId);
    /*
    spu/page?key=&saleable=true&page=1&rows=5
        key:要搜索的字段
        saleable:上架是true，下架是false，全部是不给参数
     */
    @GetMapping("spu/page")
    public PageResult<SpuBo> querySupsByPage(
            @RequestParam(name = "key",required = false) String key,
            @RequestParam(name = "saleable",required = false) String saleable,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "rows") Integer pageSize);
    /**
     * 根据spuid查询sku的集合
     * @param spuId
     * @return
     */
    @GetMapping("sku/list")
    public List<Sku> querySkusBySpuId(@RequestParam("id")Long spuId);
    /**
     * 根据spuid查询spu
     * @param id
     * @return
     */
    @GetMapping("spu/{id}")
    public Spu querySpuById(@PathVariable("id") Long id);
}
