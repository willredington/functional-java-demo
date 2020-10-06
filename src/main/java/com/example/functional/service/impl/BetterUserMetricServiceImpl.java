package com.example.functional.service.impl;

import com.example.functional.entity.User;
import com.example.functional.repository.UserRepository;
import com.example.functional.service.UserMetricService;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BetterUserMetricServiceImpl implements UserMetricService {

  private final UserRepository userRepository;

  public BetterUserMetricServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private <T> List<Map<T, List<User>>> groupUserBySomething(
      Function<User, T> getKeyCallback,
      Function<User, List<User>> callbackIfEmpty,
      BiFunction<User, List<User>, List<User>> callbackIfNotEmpty) {

    Map<T, List<User>> result = new HashMap<>();

    for (User user : userRepository.findAll()) {

      // get the key from the user
      T key = getKeyCallback.apply(user);

      // call the update map callback
      result.compute(
          key,
          (theKey, groupOfUsers) -> {
            if (groupOfUsers == null) {
              return callbackIfEmpty.apply(user);
            }

            return callbackIfNotEmpty.apply(user, groupOfUsers);
          });
    }

    // convert map to a list of maps
    return result.entrySet().stream()
        .map(entry -> Collections.singletonMap(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Map<Month, List<User>>> groupUsersByBirthMonth() {
    return groupUserBySomething(
        user -> user.getBirthDate().getMonth(),
        userToAdd -> new ArrayList<>(Collections.singletonList(userToAdd)),
        (userToAdd, usersInGroup) -> {
          usersInGroup.add(userToAdd);
          return usersInGroup;
        });
  }

  @Override
  public List<Map<Integer, List<User>>> groupUsersByAge() {
    return groupUserBySomething(
        User::getAge,
        userToAdd -> new ArrayList<>(Collections.singletonList(userToAdd)),
        (userToAdd, usersInGroup) -> {
          usersInGroup.add(userToAdd);
          return usersInGroup;
        });
  }
}
