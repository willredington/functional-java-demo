package com.example.functional.service;

import com.example.functional.entity.User;
import java.time.Month;
import java.util.List;
import java.util.Map;

public interface UserMetricService {

  List<Map<Month, List<User>>> groupUsersByBirthMonth();

  List<Map<Integer, List<User>>> groupUsersByAge();
}
