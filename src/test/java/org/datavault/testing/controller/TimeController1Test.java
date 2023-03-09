package org.datavault.testing.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import lombok.SneakyThrows;
import org.datavault.testing.app.TestingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.MimeTypeUtils;

@SpringBootTest(classes = TestingApplication.class)
@AutoConfigureMockMvc
public class TimeController1Test {

  @Autowired
  MockMvc mockMvc;


  @Test
  @SneakyThrows
  void testGetTimeUsingMockMvc(){

    MvcResult result = mockMvc.perform(get("/time/now"))
        .andExpect(content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.time").value("2023-03-09T10:27:03Z"))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    assertEquals("{\"time\":\"2023-03-09T10:27:03Z\"}", result.getResponse().getContentAsString());
  }
  @TestConfiguration
  static class TestConfig {

    @Bean
    Clock clock(){
        Instant fixed = Instant.parse("2023-03-09T10:27:03Z");
        return Clock.fixed(fixed, ZoneId.of("UTC"));
    }

  }
}
