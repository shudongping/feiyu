package com.feiyu.upms.api.feign;

/**
 * @Author shudongping
 * @Date 2019/3/27
 */

import com.feiyu.common.core.domain.Result;
import com.feiyu.upms.api.domain.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "feiyu-upms")
public interface RemoteUserService {

    @GetMapping("/user/remote/userInfo/{username}")
    Result<UserInfo> getUserInfoForRemote(@PathVariable("username") String username);


}
