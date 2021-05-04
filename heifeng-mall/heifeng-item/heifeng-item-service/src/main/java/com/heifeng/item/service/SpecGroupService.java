package com.heifeng.item.service;

import com.heifeng.item.pojo.SpecGroup;
import com.heifeng.item.mapper.SpecGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecGroupService {
    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecParamService specParamService;

    /**
     * 根据cid查询规格参数组
     * @param cid
     * @return
     */
    @Transactional
    public List<SpecGroup> querySpecGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return specGroupMapper.select(specGroup);
    }

    /**
     * 根据cid查询规格参数信息
     * @param cid
     * @return
     */
    public List<SpecGroup> querySpecsByCid(Long cid) {
        // 查询规格组
        List<SpecGroup> groups = this.querySpecGroupsByCid(cid);
        groups.forEach(g -> {
            // 查询组内参数
            g.setParams(specParamService.querySpecParamsByGid(g.getId(), null, null));
        });
        return groups;
    }
}
