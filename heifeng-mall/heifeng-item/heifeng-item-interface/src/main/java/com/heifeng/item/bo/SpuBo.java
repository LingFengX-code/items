package com.heifeng.item.bo;

import com.heifeng.item.pojo.Spu;
import com.heifeng.item.pojo.Sku;
import com.heifeng.item.pojo.SpuDetail;

import java.util.List;

/*
spu的businessObject：为了完成业务而建立的类，没有表实体
 */
public class SpuBo extends Spu {
    String cname;// 商品分类名称
    String bname;// 品牌名称
    SpuDetail spuDetail;// 商品详情
    List<Sku> skus;// sku列表


    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public SpuDetail getSpuDetail() {
        return spuDetail;
    }

    public void setSpuDetail(SpuDetail spuDetail) {
        this.spuDetail = spuDetail;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }
}
