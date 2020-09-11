package com.example.functional.service;

import com.example.functional.entity.User;

public interface UserMetricService {

  User getOldestUser();

  User getYoungestUser();
}
