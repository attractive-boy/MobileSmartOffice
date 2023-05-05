package com.mobile.smartoffice.api.service;

import com.mobile.smartoffice.api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.shiro.authc.Account;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author attractiveboy
 * @since 2023-04-22
 */
public interface IUserService extends IService<User> {
    //登录或者注册
    User loginOrRegister(String username,String phone,String password);

    String generateToken(String username, String phone);
}
