package com.example.functional.service.impl;

import com.example.functional.entity.User;
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
  public User getOldestUser() {

    int oldest = -1;
    User result = null;

    for (User user : userRepository.findAll()) {
      if (user.getAge() > oldest) {
        result = user;
      }
    }

    return result;
  }

  @Override
  public User getYoungestUser() {

    int youngest = Integer.MAX_VALUE;
    User result = null;

    for (User user : userRepository.findAll()) {
      if (user.getAge() < youngest) {
        result = user;
      }
    }

    return result;
  }
}
