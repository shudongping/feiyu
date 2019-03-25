package com.feiyu.upms.biz.service;

import com.feiyu.upms.api.domain.dto.UserInfo;
import com.feiyu.upms.api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author shudongping
 * @since 2019-03-21
 */
public interface IUserService extends IService<User> {

    UserInfo getUserInfoForRemote(String username) throws Exception;
}
