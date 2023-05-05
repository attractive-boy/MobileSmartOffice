package com.mobile.smartoffice.api.controller;


import com.mobile.smartoffice.api.entity.User;
import com.mobile.smartoffice.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author attractiveboy
 * @since 2023-04-22
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
//    构造器注入
    private final IUserService userService;
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }
    //登录请求get，携带用户名，手机号和密码
    @RequestMapping("/login")
    public String login(String username,String phone,String password){
        //调用service层的登录方法
        User user = userService.loginOrRegister(username,phone,password);
        //如果登录成功，返回token
        if(user!=null){
            return userService.generateToken(username,phone);
        }else{
            //如果登录失败，返回错误信息
            return "登录失败";
        }
    }
}
