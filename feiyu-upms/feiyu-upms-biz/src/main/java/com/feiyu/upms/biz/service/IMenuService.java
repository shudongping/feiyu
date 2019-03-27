package com.feiyu.upms.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyu.upms.api.entity.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shudongping
 * @since 2019-03-25
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenuByRoleId(String roleId);
}
