package com.example.functional.service.impl;

import com.example.functional.repository.UserRepository;
import com.example.functional.service.UserMetricService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserMetricServiceImpl implements UserMetricService {

  private final UserRepository userRepository;

  public UserMetricServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public int getOldestUser() {
    return 0;
  }

  @Override
  public int getYoungestUser() {
    return 0;
  }
}
