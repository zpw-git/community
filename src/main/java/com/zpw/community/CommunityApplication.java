package com.zpw.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@Configuration
@MapperScan(basePackages = {"com.zpw.community.Dao","com.zpw.community.Dao.Mapper"})
public class CommunityApplication {

  public static void main(String[] args) {
    SpringApplication.run(CommunityApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

}
