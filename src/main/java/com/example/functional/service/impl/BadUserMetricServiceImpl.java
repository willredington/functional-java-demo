package com.example.functional.service.impl;

import com.example.functional.entity.User;
import com.example.functional.repository.UserRepository;
import com.example.functional.service.UserMetricService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BadUserMetricServiceImpl implements UserMetricService {

  private final UserRepository userRepository;

  public BadUserMetricServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getOldestUser() {

    User result = null;

    for (User user : userRepository.findAll()) {

      if (result == null) {
        result = user;
        continue;
      }

      if (user.getAge() > result.getAge()) {
        result = user;
      }
    }

    return result;
  }

  @Override
  public User getYoungestUser() {
    User result = null;

    for (User user : userRepository.findAll()) {

      if (result == null) {
        result = user;
        continue;
      }

      if (user.getAge() < result.getAge()) {
        result = user;
      }
    }

    return result;
  }
}
