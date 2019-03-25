package com.feiyu.upms.biz.controller;


import com.feiyu.common.core.domain.Result;
import com.feiyu.upms.api.domain.dto.UserInfo;
import com.feiyu.upms.biz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author shudongping
 * @since 2019-03-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public Result<Object> getUserById(@PathVariable("id") String id) {
        return Result.success(userService.getById(id));
    }


    @GetMapping("/remote/userInfo/{username}")
    public Result<UserInfo> getUserInfoForRemote(@PathVariable("username") String username) {

    }


}
