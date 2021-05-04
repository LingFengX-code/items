package com.heifeng.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heifeng.common.pojo.PageResult;
import com.heifeng.item.bo.SpuBo;
import com.heifeng.item.mapper.*;
import com.heifeng.item.pojo.Sku;
import com.heifeng.item.pojo.Spu;
import com.heifeng.item.pojo.SpuDetail;
import com.heifeng.item.pojo.Stock;
import com.heifeng.item.mapper.*;
import com.heifeng.item.pojo.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
和商品有关的业务，都放在此业务里
 */
@Service
public class GoodsService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;

    /*
        saleable为空时，即返回全部
     */
    public PageResult<SpuBo> querySupsByPage(String key, String saleable, Integer page, Integer pageSize) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //判断saleable是上架，下架还是全部
        if(!StringUtils.isEmpty(saleable)){
            if(saleable.equals("true")) criteria.andEqualTo("saleable","1");
            if(saleable.equals("false")) criteria.andEqualTo("saleable","0");
        }
        //添加查询条件：通过title搜索
        if(!StringUtils.isEmpty(key)) criteria.andLike("title","%"+key+"%");
        //分页给出
        PageHelper.startPage(page,pageSize);
        //spuBos,cid,bid
        List<Spu> spus = spuMapper.selectByExample(example);
        List<SpuBo> spuBos = new ArrayList<>();
        spus.forEach(spu -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu,spuBo);    //拷贝相同属性
            //cname
            List<Long> cids = new ArrayList<>();
            cids.add(spu.getCid1());
            cids.add(spu.getCid2());
            cids.add(spu.getCid3());
            List<String> categorieNames = categoryService.queryCategoryNamesByCids(cids);
            StringBuffer cname = new StringBuffer();
            categorieNames.forEach(name -> {
                cname.append(name+"/");
            });
            cname.deleteCharAt(cname.length()-1); //删掉最后一个‘/’
            spuBo.setCname(cname.toString());
            //bname
            spuBo.setBname(brandMapper.selectByPrimaryKey(spu.getBrandId()).getName());
            spuBos.add(spuBo);
        });
        //返回pageResult
        PageInfo<Spu> pageInfo = new PageInfo<Spu>(spus);
        return new PageResult<>(pageInfo.getTotal(),spuBos);
    }

    /*
     根据supBo来保存信息
     */
    @Transactional
    public void saveSups(SpuBo spuBo) {
        //spu
        spuBo.setId(null);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        spuMapper.insertSelective(spuBo);
       // SpuDetail spuDetail;// 商品详情
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        spuDetailMapper.insertSelective(spuDetail);
       // List<Sku> skus;// sku列表
        spuBo.getSkus().forEach(sku -> {
            sku.setSpuId(spuBo.getId());
            sku.setId(null);
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            skuMapper.insertSelective(sku);
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockMapper.insertSelective(stock);
        });
        sendMessage(spuBo.getId(),"insert");
    }

    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        SpuDetail spuDetail = new SpuDetail();
        spuDetail.setSpuId(spuId);
        return spuDetailMapper.select(spuDetail).get(0);
    }

    public List<Sku> querySkusBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        return skuMapper.select(sku);
    }

    public Spu querySpuById(Long id) {
        return this.spuMapper.selectByPrimaryKey(id);
    }

    /**
     * MQ的消息发送：
     * exchange在配置文件的Template中已经配置好，所以只需要配置routeKey
     * @param id
     * @param type
     */
    private void sendMessage(Long id, String type){
        // 发送消息
        try {
            this.amqpTemplate.convertAndSend("item." + type, id);
        } catch (Exception e) {
            e.printStackTrace();
           // logger.error("{}商品消息发送异常，商品id：{}", type, id, e);
        }
    }
}
