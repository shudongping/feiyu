package com.feiyu.upms.api.domain.dto;

import com.feiyu.upms.api.entity.Dept;
import com.feiyu.upms.api.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @Author shudongping
 * @Date 2019/3/25
 */
@Data
public class UserInfo {

    private User user;

    private List<String> roles;

    private List<String> permissions;

    private Dept dept;

}
