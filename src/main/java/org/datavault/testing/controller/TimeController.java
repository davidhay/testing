package org.datavault.testing.controller;

import java.time.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {

  private final Clock clock;

  @Autowired
  public TimeController(Clock clock) {
    this.clock = clock;
  }

  @GetMapping("/now")
  public TimeData getTime(){
    TimeData time = new TimeData(clock.instant());
    return time;
  }

}
