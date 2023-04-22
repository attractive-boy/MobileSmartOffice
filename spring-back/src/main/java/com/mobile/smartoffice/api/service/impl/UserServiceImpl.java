package com.mobile.smartoffice.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mobile.smartoffice.api.entity.User;
import com.mobile.smartoffice.api.mapper.UserMapper;
import com.mobile.smartoffice.api.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.authc.Account;
import org.springframework.stereotype.Service;

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

}
