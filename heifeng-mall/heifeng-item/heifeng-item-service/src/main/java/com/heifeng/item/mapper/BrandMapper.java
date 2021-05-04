package com.heifeng.item.mapper;

import com.heifeng.item.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {
    @Select("SELECT b.* FROM tb_brand b INNER JOIN tb_category_brand cb ON b.id=cb.brand_id WHERE cb.category_id = #{cid}")
    List<Brand> selectBrandsByCid(Long cid);
}
