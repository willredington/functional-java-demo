package com.example.functional.service.impl;

import static org.mockito.Mockito.when;

import com.example.functional.constant.Data;
import com.example.functional.repository.UserRepository;
import com.example.functional.service.UserMetricService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Log4j2
@ExtendWith(MockitoExtension.class)
class UserMetricServiceImplTest {

  @Mock private UserRepository userRepository;

  private List<UserMetricService> servicesToTest = new ArrayList<>();

  @BeforeEach
  public void beforeEach() {
    when(userRepository.findAll()).thenReturn(Data.USERS);
    servicesToTest =
        Arrays.asList(
            new BadUserMetricServiceImpl(userRepository),
            new BetterUserMetricServiceImpl(userRepository),
            new BestUserMetricServiceImpl(userRepository));
  }

  @Test
  void shouldGroupUsersByBirthMonth() {
    for (UserMetricService service : servicesToTest) {
      TestHelper.assertMonths(service.groupUsersByBirthMonth());
    }
  }

  @Test
  void shouldGroupUsersByAges() {
    for (UserMetricService service : servicesToTest) {
      TestHelper.assertAges(service.groupUsersByAge());
    }
  }
}
