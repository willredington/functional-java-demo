package com.example.functional.service.impl;

import com.example.functional.condition.ValueCondition;
import com.example.functional.constant.Data;
import com.example.functional.entity.User;
import com.example.functional.repository.UserRepository;
import com.example.functional.service.UserMetricService;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SlightlyMuchBetterUserMetricServiceImpl implements UserMetricService {

  private final UserRepository userRepository;

  public SlightlyMuchBetterUserMetricServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private Optional<User> getUserByCondition(ValueCondition valueCondition) {
    return userRepository.findAll().stream()
        .reduce(
            (user, user2) -> valueCondition.matches(user.getAge(), user2.getAge()) ? user : user2);
  }

  @Override
  public User getOldestUser() {
    return getUserByCondition((inputValue, valueToCompare) -> inputValue > valueToCompare)
        .orElse(Data.USERS.get(0));
  }

  @Override
  public User getYoungestUser() {
    return getUserByCondition((inputValue, valueToCompare) -> inputValue < valueToCompare)
        .orElse(Data.USERS.get(0));
  }
}
