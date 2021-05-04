package com.heifeng.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heifeng.common.pojo.PageResult;
import com.heifeng.item.mapper.BrandMapper;
import com.heifeng.item.mapper.CategoryMapper;
import com.heifeng.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;    //品牌dao
    @Autowired
    private CategoryMapper categoryMapper;  //分类dao
    @Transactional
    public PageResult<Brand> queryAll(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("name","%"+key+"%").orEqualTo("letter",key);
        }
        PageHelper.startPage(page,rows);    //分页操作
        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+(desc ? "desc" : "asc"));
        }
        List<Brand> brandList = brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        return new PageResult<Brand>(pageInfo.getTotal(),pageInfo.getList());
    }
    //保存品牌（与分类表是多对多）
    @Transactional
    public boolean saveBrand(Brand brand, List<Long> cids) {
        boolean flag = false;
        flag = brandMapper.insertSelective(brand) == 1;
        if(flag){
            cids.forEach(cid -> {
                categoryMapper.insertCaregoryAndBrand(cid,brand.getId());
            });
        }
        return flag;
    }

    /**
     *
     * @param cid 分类id
     * @return 品牌集合
     */
    public List<Brand> queryBrandsByCid(Long cid) {
        return brandMapper.selectBrandsByCid(cid);
    }

    /**
     * 根据品牌id查询品牌
     * @param id
     * @return
     */
    public Brand queryBrandById(Long id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }
}
