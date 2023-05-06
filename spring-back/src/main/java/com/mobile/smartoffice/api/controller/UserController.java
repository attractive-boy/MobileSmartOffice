package com.mobile.smartoffice.api.controller;


import com.mobile.smartoffice.api.entity.User;
import com.mobile.smartoffice.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    //登录请求get，携带用户名，手机号和密码 post
    //{mobile: "123", password: "123", group: "h5"} json
    @PostMapping("/login")
    public String login(@RequestBody User userinfo){
        //调用service层的登录方法
        User user = userService.loginOrRegister(userinfo.getUsername(), userinfo.getMobile(), userinfo.getPassword());
        //如果登录成功，返回token
        if(user!=null){
            //新建一个json对象
            String token= userService.generateToken(userinfo.getUsername(),userinfo.getMobile());
            String code = "200";
            String msg = "登录成功";
            String userInfo = "{\"username\":\""+user.getUsername()+"\",\"mobile\":\""+user.getMobile()+"\",\"password\":\""+user.getPassword()+"\"}";
            return "{\"code\":\""+code+"\",\"msg\":\""+msg+"\",\"data\":\""+ token +"\",\"userInfo\":"+userInfo+"}";
        }else{
            //如果登录失败，返回错误信息
            return "登录失败";
        }
    }
}
