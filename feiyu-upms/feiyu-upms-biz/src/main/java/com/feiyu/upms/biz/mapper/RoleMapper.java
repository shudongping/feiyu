package com.feiyu.upms.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feiyu.upms.api.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getRolesByUserId(@Param("userId") String userId);
}
