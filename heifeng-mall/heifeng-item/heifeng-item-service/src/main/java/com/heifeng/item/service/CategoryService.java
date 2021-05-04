package com.heifeng.item.service;

import com.heifeng.item.mapper.CategoryMapper;
import com.heifeng.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    public List<Category> queryByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }

    @Transactional
    //根据cid的数组返回category名的数组
    public List<String> queryCategoryNamesByCids(List<Long> cids){
        Example cateoryExample = new Example(Category.class);
        Example.Criteria cateoryCriteria = cateoryExample.createCriteria();
        cids.forEach(cid->{
            cateoryCriteria.orEqualTo("id",cid);
        });
        List<Category> categories = categoryMapper.selectByExample(cateoryExample);
        //确保返回了三个列值
        if(CollectionUtils.isEmpty(categories)){
            throw new RuntimeException("查询错误");
        }
        List<String> categoryNames = new ArrayList<>();
        categories.forEach(category -> {
            categoryNames.add(category.getName());
        });
        return categoryNames;
    }
}
