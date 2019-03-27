package com.feiyu.upms.biz.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.common.core.domain.CodeMessage;
import com.feiyu.common.core.exception.ServiceException;
import com.feiyu.upms.api.domain.dto.UserInfo;
import com.feiyu.upms.api.entity.Menu;
import com.feiyu.upms.api.entity.Role;
import com.feiyu.upms.api.entity.User;
import com.feiyu.upms.biz.mapper.UserMapper;
import com.feiyu.upms.biz.service.IMenuService;
import com.feiyu.upms.biz.service.IRoleService;
import com.feiyu.upms.biz.service.IUserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Override
    public UserInfo getUserInfoForRemote(String username) throws Exception {

        UserInfo userInfo = new UserInfo();

        User user = getOne(Wrappers.<User>query().lambda().eq(User::getUserName, username));

        if (user == null) {
            throw new ServiceException(new CodeMessage(500, "用户不存在！"));
        }

        userInfo.setUser(user);

        //查询角色
        List<Role> roleList = roleService.getRolesByUserId(user.getId());
        List<String> roleIds = roleList.stream().map(Role::getId).collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, String.class));
        List<String> permissions = Lists.newArrayList();
        roleIds.stream().forEach(roleId -> {
            List<Menu> menus = menuService.getMenuByRoleId(roleId);
            menus.stream().forEach(menu -> permissions.add(menu.getPermission()));
        });
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
        return userInfo;
    }

}
