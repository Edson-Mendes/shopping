package com.emendes.discoveryserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncodeConfig {

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
