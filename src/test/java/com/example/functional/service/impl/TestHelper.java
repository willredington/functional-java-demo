package com.example.functional.service.impl;

import com.example.functional.entity.User;
import com.example.functional.exception.NotFoundException;
import java.time.Month;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

public class TestHelper {

  private static List<User> findMonthInList(List<Map<Month, List<User>>> list, Month month) {
    return list.stream()
        .filter(item -> item.containsKey(month))
        .map(item -> item.values().stream().findFirst().orElseThrow(NotFoundException::new))
        .findFirst()
        .orElseThrow(NotFoundException::new);
  }

  private static List<User> findAgeInList(List<Map<Integer, List<User>>> list, int ageToFind) {
    return list.stream()
        .filter(item -> item.containsKey(ageToFind))
        .map(item -> item.values().stream().findFirst().orElseThrow(NotFoundException::new))
        .findFirst()
        .orElseThrow(NotFoundException::new);
  }

  public static void assertMonths(List<Map<Month, List<User>>> actual) {
    Assertions.assertEquals(1, findMonthInList(actual, Month.JANUARY).size());
    Assertions.assertEquals(3, findMonthInList(actual, Month.FEBRUARY).size());
    Assertions.assertEquals(1, findMonthInList(actual, Month.MARCH).size());
    Assertions.assertEquals(2, findMonthInList(actual, Month.APRIL).size());
    Assertions.assertEquals(1, findMonthInList(actual, Month.MAY).size());
    Assertions.assertEquals(1, findMonthInList(actual, Month.NOVEMBER).size());
  }

  public static void assertAges(List<Map<Integer, List<User>>> actual) {
    Assertions.assertEquals(2, findAgeInList(actual, 11).size());
    Assertions.assertEquals(2, findAgeInList(actual, 34).size());
    Assertions.assertEquals(1, findAgeInList(actual, 44).size());
    Assertions.assertEquals(3, findAgeInList(actual, 67).size());
    Assertions.assertEquals(1, findAgeInList(actual, 100).size());
  }
}
