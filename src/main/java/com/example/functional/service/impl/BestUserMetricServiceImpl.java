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
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BestUserMetricServiceImpl implements UserMetricService {

  private final UserRepository userRepository;

  public BestUserMetricServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private <T> List<Map<T, List<User>>> groupUserByKey(Function<User, T> getKeyCallback) {

    Map<T, List<User>> result = new HashMap<>();

    for (User user : userRepository.findAll()) {

      // get the key from the user
      T groupByKey = getKeyCallback.apply(user);

      // call the update map callback
      result.compute(
          groupByKey,
          (theKey, groupOfUsers) -> {

            // create the initial list of new users to add
            List<User> usersToAdd = new ArrayList<>();
            usersToAdd.add(user);

            // if it doesn't exists, add a new list with the initial user
            if (groupOfUsers == null) {
              return usersToAdd;
            }

            // if it already exists, update the existing list of users
            groupOfUsers.addAll(usersToAdd);

            return groupOfUsers;
          });
    }

    // convert map to a list of maps
    return result.entrySet().stream()
        .map(entry -> Collections.singletonMap(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Map<Month, List<User>>> groupUsersByBirthMonth() {
    return groupUserByKey(user -> user.getBirthDate().getMonth());
  }

  @Override
  public List<Map<Integer, List<User>>> groupUsersByAge() {
    return groupUserByKey(User::getAge);
  }
}
