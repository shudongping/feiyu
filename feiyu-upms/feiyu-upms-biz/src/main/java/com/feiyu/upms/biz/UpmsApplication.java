package com.feiyu.upms.biz;

import com.feiyu.common.core.security.config.ResourceServerAutoConfiguration;
import com.feiyu.common.core.security.config.ResourceServerSecurityConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @Author shudongping
 * @Date 2019/3/21
 */
@SpringBootApplication
@Import({ResourceServerAutoConfiguration.class})
@MapperScan("com.feiyu.upms.biz.mapper")
public class UpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }

}
