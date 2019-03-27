package com.feiyu.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import com.feiyu.common.core.domain.Result;
import com.feiyu.common.security.utils.FeiyuUser;
import com.feiyu.upms.api.domain.dto.UserInfo;
import com.feiyu.upms.api.feign.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author shudongping
 * @Date 2019/3/25
 */
@Service
public class FeiyuUserDetailService implements UserDetailsService {

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Result<UserInfo> result = remoteUserService.getUserInfoForRemote(username);

        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserInfo userInfo = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();

        if (ArrayUtil.isNotEmpty(userInfo.getRoles())) {
            // 获取角色
            Arrays.stream(userInfo.getRoles()).forEach(roleId -> dbAuthsSet.add("ROLE_" + roleId));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(userInfo.getPermissions()));

        }

        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));

        return new FeiyuUser(userInfo.getUser().getId(), userInfo.getDept().getId(), userInfo.getDept().getDeptCode(), userInfo.getUser().getUserName(),
                userInfo.getUser().getPassword(), true, true, true, true, authorities);
    }
}
