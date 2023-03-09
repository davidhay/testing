package org.datavault.testing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.datavault.testing.calc.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceMockitoTest {

  CalculatorService service;

  @Mock
  Calculator mCalc;

  @BeforeEach
  void setup() {
    this.service = new CalculatorService(mCalc);
  }
  @Test
  void testServiceAdd1() {
    when(mCalc.add(1,2)).thenReturn(-3);

    int result = service.add(1,2);
    assertEquals(-3, result);

    verify(mCalc).add(1,2);

    verifyNoMoreInteractions(mCalc);
  }

  @Test
  void testServiceAdd2() {
    doReturn(-33).when(mCalc).add(1,2);

    int result = service.add(1,2);
    assertEquals(-33, result);

    verify(mCalc).add(1,2);

    verifyNoMoreInteractions(mCalc);
  }

  @Test
  void testServiceAdd3() {
    doAnswer(new Answer<>() {
      @Override
      public Integer answer(InvocationOnMock invocation) {
        int a = invocation.getArgument(0);
        int b = invocation.getArgument(1);
        return 0 - (a*b);
      }
    }).when(mCalc).add(1,2);

    int result = service.add(1,2);
    assertEquals(-2, result);

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
