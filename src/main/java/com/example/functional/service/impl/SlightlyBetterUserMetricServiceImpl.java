package com.example.functional.service.impl;

import com.example.functional.condition.ValueCondition;
import com.example.functional.entity.User;
import com.example.functional.repository.UserRepository;
import com.example.functional.service.UserMetricService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SlightlyBetterUserMetricServiceImpl implements UserMetricService {

  private final UserRepository userRepository;

  public SlightlyBetterUserMetricServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private boolean compareUser(User user, int valueToCheck, ValueCondition condition) {
    return condition.matches(user.getAge(), valueToCheck);
  }

  private User getUserByCondition(ValueCondition valueCondition) {

    User result = null;

    for (User user : userRepository.findAll()) {

      if (result == null) {
        result = user;
        continue;
      }

      boolean matchesCondition = compareUser(user, result.getAge(), valueCondition);

      if (matchesCondition) {
        result = user;
      }
    }

    return result;
  }

  @Override
  public User getOldestUser() {
    return getUserByCondition((inputValue, valueToCompare) -> inputValue > valueToCompare);
  }

  @Override
  public User getYoungestUser() {
    return getUserByCondition((inputValue, valueToCompare) -> inputValue < valueToCompare);
  }
}
