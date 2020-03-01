package com.zpw.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/10-12:09
 */

@Configuration
public class Webconfig implements WebMvcConfigurer {

  @Autowired
  SessionInterceptor sessionInterceptor;
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
  }
}
