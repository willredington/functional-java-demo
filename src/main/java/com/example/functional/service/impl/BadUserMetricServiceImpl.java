package com.example.functional.service.impl;

import com.example.functional.entity.User;
import com.example.functional.repository.UserRepository;
import com.example.functional.service.UserMetricService;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public List<Map<Month, List<User>>> groupUsersByBirthMonth() {

    List<Map<Month, List<User>>> groups = new ArrayList<>();

    for (User user : userRepository.findAll()) {

      Month month = user.getBirthDate().getMonth();

      // create the default with the current user
      List<User> usersInMonth = new ArrayList<>();
      usersInMonth.add(user);

      boolean foundMatch = false;

      // look through the groups to see if it already exists
      for (Map<Month, List<User>> group : groups) {

        // if we've got a matching month, continue to next user
        if (group.containsKey(month)) {
          List<User> usersInGroup = group.get(month);
          usersInGroup.addAll(usersInMonth);
          foundMatch = true;
          break;
        }
      }

      // if no match, add the group
      if (!foundMatch) {
        Map<Month, List<User>> group = new HashMap<>();
        group.put(month, usersInMonth);
        groups.add(group);
      }
    }

    return groups;
  }

  @Override
  public List<Map<Integer, List<User>>> groupUsersByAge() {

    List<Map<Integer, List<User>>> groups = new ArrayList<>();

    for (User user : userRepository.findAll()) {

      Integer age = user.getAge();

      // create the default with the current user
      List<User> usersWithSameAge = new ArrayList<>();
      usersWithSameAge.add(user);

      boolean foundMatch = false;

      // look through the groups to see if it already exists
      for (Map<Integer, List<User>> group : groups) {

        // if we've got a matching month, continue to next user
        if (group.containsKey(age)) {
          List<User> usersInGroup = group.get(age);
          usersInGroup.addAll(usersWithSameAge);
          foundMatch = true;
          break;
        }
      }

      // if no match, add the group
      if (!foundMatch) {
        Map<Integer, List<User>> group = new HashMap<>();
        group.put(age, usersWithSameAge);
        groups.add(group);
      }
    }

    return groups;
  }
}
