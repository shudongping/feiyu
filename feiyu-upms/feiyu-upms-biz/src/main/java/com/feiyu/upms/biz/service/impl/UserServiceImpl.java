package com.feiyu.upms.biz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.upms.api.domain.dto.UserInfo;
import com.feiyu.upms.api.entity.User;
import com.feiyu.upms.biz.mapper.UserMapper;
import com.feiyu.upms.biz.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author shudongping
 * @since 2019-03-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserInfo getUserInfoForRemote(String username) throws Exception {

        User user = getOne(Wrappers.<User>query().lambda().eq(User::getUserName,username));

        return null;
    }

}
