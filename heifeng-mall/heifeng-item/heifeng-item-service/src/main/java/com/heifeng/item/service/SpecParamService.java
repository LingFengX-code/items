package com.heifeng.item.service;

import com.heifeng.item.mapper.SpecParamMapper;
import com.heifeng.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecParamService {
    @Autowired
    private SpecParamMapper specParamMapper;

    /*

     */
    @Transactional
    public List<SpecParam> querySpecParamsByGid(Long gid, Boolean generic, Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setGeneric(generic);
        specParam.setSearching(searching);
        return specParamMapper.select(specParam);
    }

    public List<SpecParam> querySpecParamsByCid(Long cid,Boolean generic,Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setCid(cid);
        specParam.setGeneric(generic);
        specParam.setSearching(searching);
        return specParamMapper.select(specParam);
    }
}
