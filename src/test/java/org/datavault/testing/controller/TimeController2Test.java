package org.datavault.testing.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import lombok.SneakyThrows;
import org.datavault.testing.app.TestingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;

@SpringBootTest(classes = TestingApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimeController2Test {

  private static final Instant TEST_TIME = Instant.parse("2023-03-09T10:27:03Z");

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  @SneakyThrows
  void testGetTimeUsingMockMvc(){
    ResponseEntity<TimeData> response = restTemplate.getForEntity("/time/now", TimeData.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(MimeTypeUtils.APPLICATION_JSON, response.getHeaders().getContentType());
    TimeData expected = new TimeData(TEST_TIME);
    assertEquals(expected, response.getBody());
  }

  @TestConfiguration
  static class TestConfig {

    @Bean
    Clock clock() {
        return Clock.fixed(TEST_TIME, ZoneId.of("UTC"));
    }

  }
}
