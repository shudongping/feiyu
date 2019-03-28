package com.feiyu.common.security.config;


import com.feiyu.common.security.handler.CustomAccessDeniedHandler;
import com.feiyu.common.security.handler.ResourceAuthExceptionEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author shudongping
 * @date 2018/09/11
 */
@Configuration
@EnableResourceServer
@Slf4j
public class ResourceServerSecurityConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    @Autowired
    private UrlsIgnorePropertiesConfig urlsIgnorePropertiesConfig;

    @Autowired
    protected RemoteTokenServices remoteTokenServices;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String[] AUTH_WHITELIST = {
            "/**/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "swagger-resources/configuration/ui",
            "/doc.html", "/**"

    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        for (String au : AUTH_WHITELIST
                ) {
            http.authorizeRequests().antMatchers(au).permitAll();
        }
        urlsIgnorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        http.authorizeRequests().anyRequest().authenticated().and().cors().and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        DefaultUserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
        userTokenConverter.setUserDetailsService(userDetailsService);
        accessTokenConverter.setUserTokenConverter(userTokenConverter);

        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
                .tokenServices(remoteTokenServices);
    }


}
