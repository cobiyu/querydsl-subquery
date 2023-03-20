package com.example.querydslsubquery.config;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLTemplates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {
  // mysql (배포시 사용) : @Profile() 로 환경별로 dynamic하게 사용 가능
  // @Bean
  public SQLTemplates mysqlTemplates() {
    return MySQLTemplates.builder().build();
  }

  // h2 사용시에 (로컬 테스트에 사용)
  @Bean
  public SQLTemplates h2Templates() {
    return H2Templates.builder().build();
  }
}
