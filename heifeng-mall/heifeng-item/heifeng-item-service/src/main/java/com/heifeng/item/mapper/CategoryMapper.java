package com.heifeng.item.mapper;

import com.heifeng.item.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<Category> {
    @Insert(value = "insert into tb_category_brand(category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCaregoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);
}
