package com.feiyu.common.security.utils;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class FeiyuUser extends User {

    private static final long serialVersionUID = 7871204234389814247L;

    @Getter
    private String id;
    @Getter
    private String deptId;
    @Getter
    private String deptCode;

    public FeiyuUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public FeiyuUser(String id, String deptId, String deptCode, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
        this.deptCode = deptCode;
    }

    public static void main(String[] args) {

    }

}
