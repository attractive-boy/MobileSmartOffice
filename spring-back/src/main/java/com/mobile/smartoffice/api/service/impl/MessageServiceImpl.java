package com.mobile.smartoffice.api.service.impl;

import com.mobile.smartoffice.api.entity.Message;
import com.mobile.smartoffice.api.mapper.MessageMapper;
import com.mobile.smartoffice.api.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
