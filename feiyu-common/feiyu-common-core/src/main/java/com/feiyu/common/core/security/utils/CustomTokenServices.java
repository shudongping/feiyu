package com.feiyu.common.core.security.utils;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 该类 处理 从认证服务获取用户信息只能获取到用户名问题
 * @Author shudongping
 * @Date 2018/10/24
 */
public class CustomTokenServices extends UserInfoTokenServices {

    public CustomTokenServices(String userInfoEndpointUrl, String clientId) {
        super(userInfoEndpointUrl, clientId);
    }


    @Override
    protected Object getPrincipal(Map<String, Object> map) {

        Map<String, Object> principalmap = (Map<String, Object>) map.get("principal");

        Set<String> dbAuthsSet = new HashSet<>();

        Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));

        FeiyuUser cloudPlatformUser = new FeiyuUser(principalmap.get("id").toString(), principalmap.get("orgId").toString(),
                principalmap.get("orgCode").toString(), principalmap.get("username").toString(), "", true, true,
                true, true, authorities
        );

        return cloudPlatformUser;
    }
}
