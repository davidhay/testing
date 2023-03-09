package org.datavault.testing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.datavault.testing.app.TestingApplication;
import org.datavault.testing.calc.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = TestingApplication.class)
public class CalculatorServiceMockBeanTest {

  @Autowired
  CalculatorService service;

  @MockBean
  Calculator mCalc;

  @Test
  void testServiceAdd() {
    when(mCalc.add(1,2)).thenReturn(-3);

    int result = service.add(1,2);
    assertEquals(-3, result);

    verify(mCalc).add(1,2);

    verifyNoMoreInteractions(mCalc);
  }

  @Test
  void testServiceSubtract() {
    when(mCalc.subtract(1,2)).thenReturn(123);

    int result = service.subtract(1,2);
    assertEquals(123, result);

    verify(mCalc).subtract(1,2);

    verifyNoMoreInteractions(mCalc);
  }

}
