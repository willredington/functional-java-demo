package com.example.functional.config;

import com.example.functional.constant.Data;
import com.example.functional.repository.UserRepository;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class InitializeConfig {

  private final UserRepository userRepository;

  public InitializeConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostConstruct
  public void init() {
    userRepository.saveAll(Data.USERS);
    log.info("initialized data");
  }
}
