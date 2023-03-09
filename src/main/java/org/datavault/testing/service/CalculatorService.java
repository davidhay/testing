package org.datavault.testing.service;

import org.datavault.testing.calc.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

  private final Calculator calculator;

  @Autowired
  public CalculatorService(Calculator calculator) {
    this.calculator = calculator;
  }

  public int add(int a, int b) {
    return calculator.add(a,b);
  }

  public int subtract(int a, int b) {
    return calculator.subtract(a,b);
  }
}
