package com.example.functional.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.functional.constant.Data;
import com.example.functional.entity.User;
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
            new SlightlyBetterUserMetricServiceImpl(userRepository),
            new MuchBetterUserMetricServiceImpl(userRepository),
            new SlightlyMuchBetterUserMetricServiceImpl(userRepository),
            new BestUserMetricServiceImpl(userRepository));
  }

  @Test
  void getOldestUser() {

    for (UserMetricService service : servicesToTest) {

      log.info("get oldest user for service {}", service.getClass().getSimpleName());

      User oldestUser = service.getOldestUser();

      assertNotNull(oldestUser);
      assertEquals(120, oldestUser.getAge());
    }
  }

  @Test
  void getYoungestUser() {

    for (UserMetricService service : servicesToTest) {

      log.info("get youngest user for service {}", service.getClass().getSimpleName());

      User youngestUser = service.getYoungestUser();

      assertNotNull(youngestUser);
      assertEquals(3, youngestUser.getAge());
    }
  }
}
