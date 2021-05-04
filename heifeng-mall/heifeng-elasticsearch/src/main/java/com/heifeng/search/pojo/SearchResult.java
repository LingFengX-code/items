package com.heifeng.search.pojo;

import com.heifeng.common.pojo.PageResult;
import com.heifeng.item.pojo.Brand;

import java.util.List;
import java.util.Map;

public class SearchResult extends PageResult<Goods> {
    private List<Map<String, Object>> categories;   //分类：key为分类id，value为分类名（如手机）
    private List<Brand> brands; //品牌
    private List<Map<String, Object>> specs;    //规格参数：key为规格参数名，value为规格参数聚合后的key（List集合）

    public SearchResult() {
    }


    public SearchResult(List<Goods> items, Long total, List<Map<String, Object>> categories, List<Brand> brands,List<Map<String, Object>> specs) {
        super(total, items);
        this.categories = categories;
        this.brands = brands;
        this.specs = specs;
    }

    public SearchResult(List<Goods> items, Long total, Integer totalPage, List<Map<String, Object>> categories, List<Brand> brands,List<Map<String, Object>> specs) {
        super(total, totalPage, items);
        this.categories = categories;
        this.brands = brands;
        this.specs = specs;
    }

    public SearchResult(List<Map<String, Object>> categories, List<Brand> brands,List<Map<String, Object>> specs) {
        this.categories = categories;
        this.brands = brands;
        this.specs = specs;
    }

    public List<Map<String, Object>> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Map<String, Object>> specs) {
        this.specs = specs;
    }

    public List<Map<String, Object>> getCategories() {
        return categories;
    }

    public void setCategories(List<Map<String, Object>> categories) {
        this.categories = categories;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
