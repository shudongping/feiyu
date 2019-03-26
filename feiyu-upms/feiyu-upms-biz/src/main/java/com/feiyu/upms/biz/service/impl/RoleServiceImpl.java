package com.feiyu.upms.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.upms.api.entity.Role;
import com.feiyu.upms.biz.mapper.RoleMapper;
import com.feiyu.upms.biz.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shudongping
 * @since 2019-03-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRolesByUserId(String userId) {
        return roleMapper.getRolesByUserId(userId);
    }

}
