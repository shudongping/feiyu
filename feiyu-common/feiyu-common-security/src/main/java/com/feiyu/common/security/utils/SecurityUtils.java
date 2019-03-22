package com.feiyu.common.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author shudongping
 * @Date 2018/10/24
 */
public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /**
     * 获取用户
     */
    public static FeiyuUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof FeiyuUser) {
            return (FeiyuUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public static FeiyuUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

}
