package org.datavault.testing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.datavault.testing.calc.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceSpyTest {

  CalculatorService service;

  Calculator sCalc = spy(new Calculator() {
    @Override
    public int add(int a, int b) {
      return a+b;
    }

    @Override
    public int subtract(int a, int b) {
      throw new UnsupportedOperationException("oops");
    }
  });

  @BeforeEach
  void setup() {
    this.service = new CalculatorService(sCalc);
  }

  // we've not changed the behaviour - but we can still verify calls
  @Test
  void testServiceAdd() {
    service.add(1,2);
    verify(sCalc).add(1,2);
    verifyNoMoreInteractions(sCalc);
  }

  @Test
  void testServiceSubtract() {

    UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class,
        () -> service.subtract(1, 2));

    assertEquals("oops", ex.getMessage());
    
    verify(sCalc).subtract(1,2);
    verifyNoMoreInteractions(sCalc);
  }

  @Test
  void testServiceOverrideSubtract() {

    // when using spies, you have to use doReturn
    doReturn(12345).when(sCalc).subtract(111,222);

    int result = service.subtract(111, 222);
    assertEquals(12345, result);

    verify(sCalc).subtract(111,222);
    verifyNoMoreInteractions(sCalc);
  }

}
