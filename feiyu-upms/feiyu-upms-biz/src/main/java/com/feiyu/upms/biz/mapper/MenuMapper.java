package com.feiyu.upms.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feiyu.upms.api.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author shudongping
 * @since 2019-03-25
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getMenuByRoleId(@Param("roleId") String roleId);
}
