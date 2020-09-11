package com.example.functional.constant;

import com.example.functional.entity.User;
import java.util.Arrays;
import java.util.List;

public class Data {

  public static final List<User> USERS =
      Arrays.asList(
          new User(1L, "bill", 12),
          new User(1L, "jake", 11),
          new User(1L, "chris", 34),
          new User(1L, "mike", 45),
          new User(1L, "markus", 28),
          new User(1L, "jill", 67),
          new User(1L, "jane", 120),
          new User(1L, "jack", 3),
          new User(1L, "steven", 53));
}
