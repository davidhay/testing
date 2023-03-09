package org.datavault.testing.config;

import org.datavault.testing.calc.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorConfig {

  @Bean
  Calculator calculator() {
    return new Calculator() {
      @Override
      public int add(int a, int b) {
        return a+b;
      }

      @Override
      public int subtract(int a, int b) {
        return a-b;
      }
    };
  }

}
