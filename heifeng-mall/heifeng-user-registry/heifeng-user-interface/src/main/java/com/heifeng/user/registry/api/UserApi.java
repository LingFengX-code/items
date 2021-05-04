package com.heifeng.user.registry.api;

import com.heifeng.user.registry.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    ///check/{data}/{type}
    /**
     * 校验数据是否可用,已经存在即为不可用
     * @param data  要校验的数据
     * @param type  要校验的数据类型：1.用户名  2.手机
     * @return 可用或不可用
     */
    @GetMapping("check/{data}/{type}")
    public Boolean checkUserData(
            @PathVariable("data") String data,
            @PathVariable(value = "type") Integer type);

    /**
     * 根据用户名和密码查询用户,校验密码是否正确
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    public User queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );


}
