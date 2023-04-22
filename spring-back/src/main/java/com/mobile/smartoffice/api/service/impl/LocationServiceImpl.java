package com.mobile.smartoffice.api.service.impl;

import com.mobile.smartoffice.api.entity.Location;
import com.mobile.smartoffice.api.mapper.LocationMapper;
import com.mobile.smartoffice.api.service.ILocationService;
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
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements ILocationService {

}
