package com.heifeng.item.service.test;

import com.heifeng.item.HeifengItemServiceApplication;
import com.heifeng.item.pojo.Brand;
import com.heifeng.item.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeifengItemServiceApplication.class)
public class BrandServiceTest {
    @Autowired
    private BrandService brandService;

    @Test
    public void queryBrandById(){
        Brand brand = brandService.queryBrandById(1528l);
        System.out.println(brand.getName());
    }
}
