package com.geo.i.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {
  @Bean
  public EntityManagerFactory entityManagerFactory() {
    return Persistence.createEntityManagerFactory("JPA-First");
  }
}
