package com.example.functional.constant;

import com.example.functional.entity.User;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Data {

  public static final List<User> USERS =
      Arrays.asList(
          new User(1L, "bill", 11, LocalDate.of(2001, 1, 1)),
          new User(2L, "jake", 11, LocalDate.of(2001, 2, 1)),
          new User(3L, "chris", 34, LocalDate.of(2001, 2, 1)),
          new User(4L, "mike", 34, LocalDate.of(2001, 2, 1)),
          new User(5L, "markus", 44, LocalDate.of(2001, 3, 1)),
          new User(6L, "jill", 67, LocalDate.of(2001, 4, 1)),
          new User(7L, "jane", 67, LocalDate.of(2001, 11, 1)),
          new User(8L, "jack", 67, LocalDate.of(2001, 4, 1)),
          new User(9L, "steven", 100, LocalDate.of(2001, 5, 1)));
}
