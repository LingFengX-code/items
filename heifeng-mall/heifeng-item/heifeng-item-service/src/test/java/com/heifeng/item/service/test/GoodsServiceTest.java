package com.heifeng.item.service.test;

import com.heifeng.item.HeifengItemServiceApplication;
import com.heifeng.item.bo.SpuBo;
import com.heifeng.item.pojo.Brand;
import com.heifeng.item.pojo.SpecParam;
import com.heifeng.item.pojo.SpuDetail;
import com.heifeng.item.pojo.Sku;
import com.heifeng.item.service.BrandService;
import com.heifeng.item.service.GoodsService;
import com.heifeng.item.service.SpecParamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes= HeifengItemServiceApplication.class)
@RunWith(SpringRunner.class)
public class GoodsServiceTest {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private SpecParamService specParamService;

    /*{brandId: 6522, title: "朵唯测试", subTitle: "",…}
        brandId: 6522
        cid1: 74
        cid2: 75
        cid3: 76
        skus: [{price: "0.00", stock: 0, indexes: "0_0_0", enable: true, title: "朵唯测试 red 4g 16g", images: "",…}]
            0: {price: "0.00", stock: 0, indexes: "0_0_0", enable: true, title: "朵唯测试 red 4g 16g", images: "",…}
            enable: true
            images: ""
            indexes: "0_0_0"
            ownSpec: "{"4":"red","12":"4g","13":"16g"}"
            price: "0.00"
            stock: 0
            title: "朵唯测试 red 4g 16g"
        spuDetail: {packingList: "", afterService: "", description: "<p>富文本编辑器</p>",…}
            afterService: ""
            description: "<p>富文本编辑器</p>"
            genericSpec: "{"1":"朵唯","2":"测试版","3":"2019","5":"100","6":"哈哈","7":"os","8":"cpu","9":"cpu-xlf"}"
            packingList: ""
            specialSpec: "{"4":["red","blue"],"12":["4g","8g","16g"],"13":["16g","32g"]}"
        subTitle: ""
        title: "朵唯测试"*/
    @Test
    public void goodsTest(){
        SpuBo spuBo = new SpuBo();
        //spu
        spuBo.setTitle("朵唯测试");
        spuBo.setSubTitle(" ");
        spuBo.setCid1(74l);
        spuBo.setCid2(75l);
        spuBo.setCid3(76l);
        spuBo.setBrandId(6522l);
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        //spuDetail
        SpuDetail spuDetail = new SpuDetail();
        spuDetail.setDescription("<p>富文本编辑器</p>");
        spuDetail.setGenericSpec("{\"1\":\"朵唯\",\"2\":\"测试版\",\"3\":\"2019\",\"5\":\"100\",\"6\":\"哈哈\",\"7\":\"os\",\"8\":\"cpu\",\"9\":\"cpu-xlf\"}");
        spuDetail.setSpecialSpec("{\"4\":[\"red\",\"blue\"],\"12\":[\"4g\",\"8g\",\"16g\"],\"13\":[\"16g\",\"32g\"]}");
        spuDetail.setPackingList(" ");
        spuDetail.setAfterService(" ");
        spuBo.setSpuDetail(spuDetail);
        // List<Sku> skus;// sku列表
        List<Sku> skus = new ArrayList<>();
        Sku sku = new Sku();
        sku.setTitle("朵唯测试 red 4g 16g");
        sku.setImages(" ");
        sku.setPrice(10l);
        sku.setIndexes("0_0_0");
        sku.setOwnSpec("{\"4\":\"red\",\"12\":\"4g\",\"13\":\"16g\"}");
        sku.setEnable(true);
        sku.setStock(0);
        skus.add(sku);
        spuBo.setSkus(skus);
        goodsService.saveSups(spuBo);
    }
    @Test
    public void brandsTest(){
        List<Brand> brands = brandService.queryBrandsByCid(1l);
        brands.forEach(brand -> {
            System.out.println(brand.getName());
        });
    }
    @Test
    public void paramsTest(){
        List<SpecParam> specParams = specParamService.querySpecParamsByCid(76l,null,true);
        specParams.forEach(specParam -> {
            System.out.println(specParam.getName());
        });
    }
}
