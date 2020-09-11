package com.example.functional.service.impl;

import com.example.functional.entity.User;
import com.example.functional.exception.NotFoundException;
import com.example.functional.repository.UserRepository;
import com.example.functional.service.UserMetricService;
import java.util.Optional;
import java.util.function.BiPredicate;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BestUserMetricServiceImpl implements UserMetricService {

  private final UserRepository userRepository;

  public BestUserMetricServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private Optional<User> getUserByCondition(BiPredicate<Integer, Integer> callback) {
    return userRepository.findAll().stream()
        .reduce((user, user2) -> callback.test(user.getAge(), user2.getAge()) ? user : user2);
  }

  @Override
  public User getOldestUser() {
    return getUserByCondition((inputValue, valueToCompare) -> inputValue > valueToCompare)
        .orElseThrow(NotFoundException::new);
  }

  @Override
  public User getYoungestUser() {
    return getUserByCondition((inputValue, valueToCompare) -> inputValue < valueToCompare)
        .orElseThrow(NotFoundException::new);
  }
}
