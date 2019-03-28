package com.feiyu.auth;

import com.feiyu.common.security.config.ResourceServerAutoConfiguration;
import com.feiyu.upms.api.feign.RemoteUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * @Author shudongping
 * @Date 2019/3/22
 */

@SpringCloudApplication
@EnableFeignClients({"com.feiyu.upms.api.feign"})
@Import(value = {ResourceServerAutoConfiguration.class, RemoteUserService.class})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
