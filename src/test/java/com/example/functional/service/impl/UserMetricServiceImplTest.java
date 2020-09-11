package com.example.functional.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.functional.constant.Data;
import com.example.functional.repository.UserRepository;
import com.example.functional.service.UserMetricService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserMetricServiceImplTest {

  @Mock private UserRepository userRepository;

  @BeforeEach
  public void beforeEach() {
    when(userRepository.findAll()).thenReturn(Data.USERS);
  }

  @Test
  void getOldestUser() {
    UserMetricService service = new UserMetricServiceImpl(userRepository);
    assertEquals(120, service.getOldestUser());
  }

  @Test
  void getYoungestUser() {
    UserMetricService service = new UserMetricServiceImpl(userRepository);
    assertEquals(3, service.getYoungestUser());
  }
}
