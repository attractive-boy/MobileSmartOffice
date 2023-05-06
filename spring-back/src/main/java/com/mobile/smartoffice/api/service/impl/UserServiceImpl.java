package com.mobile.smartoffice.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mobile.smartoffice.api.entity.User;
import com.mobile.smartoffice.api.mapper.UserMapper;
import com.mobile.smartoffice.api.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.authc.Account;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author attractiveboy
 * @since 2023-04-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
//登录或者注册
    public User loginOrRegister(String username,String phone,String password){
        //根据用户名或手机号判断是不是有这个用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username).or().eq("mobile",phone);
        User user = this.getOne(queryWrapper);
        //如果有，判断密码是否正确
        if(user!=null){
            if(user.getPassword().equals(password)){
                //如果密码正确，返回token
                return user;
            }else{
                //如果密码不正确，返回错误信息
                return null;
            }
        }else{
            //如果没有用户，注册
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setMobile(phone);
            newUser.setPassword(password);
            //更新时间和创建时 LocalDateTime类型
            newUser.setCreateTime(LocalDateTime.now());
            newUser.setUpdateTime(LocalDateTime.now());
            //role_id
            newUser.setRoleId(1);
            //注册成功，返回token
            if(this.save(newUser)){
                return newUser;
            }else{
                //注册失败，返回错误信息
                return null;
            }
        }
    }

    public String generateToken(String username, String phone) {
        try {
            // 指定算法
            Algorithm algorithm = Algorithm.HMAC256("secret");

            // 设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");

            // 生成签名
            return JWT.create()
                    .withHeader(header).withClaim("username", username).withClaim("phone", phone)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
}
