package org.datavault.testing.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CaptorTest {

  @InjectMocks
  Processor processor;

  @Mock
  Listener mListener;

  @Captor
  ArgumentCaptor<Collection<String>> argStringColl;


  @ParameterizedTest
  @ValueSource(ints = {0,1,2,3,4,5})
  void testProcess(int size){

    // this captures the argument, so we can test is later
    // good for when it's hard to create a matching argument for 'when'

    doNothing().when(mListener).listen(argStringColl.capture());

    processor.process(size);
    Collection<String> arg = argStringColl.getValue();
    assertEquals(size, arg.size());

    verify(mListener).listen(argStringColl.getValue());
    verifyNoMoreInteractions(mListener);
  }

  public static class Processor{

    private final Listener listener;

    public Processor(Listener listener) {
      this.listener = listener;
    }

    void process(int size) {
      List<String> items = new ArrayList();
      for(int i=0;i<size;i++){
        items.add("blah");
      }
      listener.listen(items);
    }
  }
  interface Listener {
      void listen(Collection<String> info);
  }

}
