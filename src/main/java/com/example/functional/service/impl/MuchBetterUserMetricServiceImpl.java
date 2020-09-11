package com.example.functional.service.impl;

import com.example.functional.condition.ValueCondition;
import com.example.functional.entity.User;
import com.example.functional.repository.UserRepository;
import com.example.functional.service.UserMetricService;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MuchBetterUserMetricServiceImpl implements UserMetricService {

  private final UserRepository userRepository;

  public MuchBetterUserMetricServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private User getUserByCondition(ValueCondition valueCondition) {

    Optional<User> userOptional =
        userRepository.findAll().stream()
            .reduce(
                (user, user2) ->
                    valueCondition.matches(user.getAge(), user2.getAge()) ? user : user2);

    if (userOptional.isPresent()) {
      return userOptional.get();
    }

    return null;
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
