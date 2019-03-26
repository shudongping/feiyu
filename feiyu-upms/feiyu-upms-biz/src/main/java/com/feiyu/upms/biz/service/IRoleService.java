package com.feiyu.upms.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyu.upms.api.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shudongping
 * @since 2019-03-25
 */
public interface IRoleService extends IService<Role> {

    List<Role> getRolesByUserId(String userId);
}
