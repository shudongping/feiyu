package com.feiyu.upms.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.upms.api.entity.Menu;
import com.feiyu.upms.biz.mapper.MenuMapper;
import com.feiyu.upms.biz.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMappepr;

    @Override
    public List<Menu> getMenuByRoleId(String roleId) {
        return menuMappepr.getMenuByRoleId(roleId);
    }


}
