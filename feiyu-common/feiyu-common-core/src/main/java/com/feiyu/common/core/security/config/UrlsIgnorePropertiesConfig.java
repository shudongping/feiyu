package com.feiyu.common.core.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shudongping
 * @Date 2018/10/9
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
public class UrlsIgnorePropertiesConfig {

    private List<String> urls = new ArrayList<>();

}
