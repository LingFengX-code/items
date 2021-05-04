package com.heifeng.auth.test;

import com.heifeng.auth.HeifengAuthApplication;
import com.heifeng.auth.controller.AuthController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = HeifengAuthApplication.class)
@RunWith(SpringRunner.class)
public class AuthenticationTest {
    @Autowired
    private AuthController authController;
    @Test
    public void test(){
        //authController.authentication()
    }
}
