package com.heifeng.search.service;

import com.heifeng.common.pojo.PageResult;
import com.heifeng.search.HeifengSearchApplication;
import com.heifeng.search.client.GoodsClient;
import com.heifeng.search.pojo.Goods;
import com.heifeng.search.repository.GoodsRepository;
import com.heifeng.item.bo.SpuBo;
import com.heifeng.item.pojo.Spu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeifengSearchApplication.class)
public class SearchServiceTest {
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private SearchService searchService;

    //创建索引库
    @Test
    public void createIndex(){
        // 创建索引库，以及映射
        this.elasticsearchTemplate.createIndex(Goods.class);
        this.elasticsearchTemplate.putMapping(Goods.class);
    }
    //elasticsearch导入数据
    @Test
    public void buildGoods(){
        Integer page = 1;
        Integer pageSize = 100;
        do{
            //分批查询到数据
            PageResult<SpuBo> pageResult = goodsClient.querySupsByPage(null, null, page, pageSize);
            List<Goods> goodsList = pageResult.getItems().stream().map(spuBo -> {
                try {
                    return searchService.buildGoods((Spu) spuBo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            goodsRepository.saveAll(goodsList);
            //如果页面不满100页即是最后一页
            pageSize = pageResult.getItems().size();
            page++;
            System.out.println(goodsList.size());
        }while (pageSize==100);

    }
}
