/*
 *功能：进行拦截行为
 *日期：2024/6/12 9:33
 *作者：
 */
package com.wms.springboot.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration   //表示配置类
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor())       //   配置jwt的拦截器规则
                .addPathPatterns("/**")//   /** 表示拦截所有请求路径
                .excludePathPatterns("/login","/server","/server/1/1");//设置权限放开


        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){  //通过@Bean注入到spring容器中
        return new JwtInterceptor();
    }

}
