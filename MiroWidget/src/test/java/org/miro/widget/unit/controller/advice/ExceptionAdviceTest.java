package org.miro.widget.unit.controller.advice;

import org.junit.jupiter.api.Test;
import org.miro.widget.controller.advice.ExceptionAdvice;
import org.miro.widget.dto.response.ResponseError;
import org.miro.widget.exceptions.ReadDataException;
import org.miro.widget.exceptions.RequestException;
import org.miro.widget.exceptions.WidgetCreateException;
import org.miro.widget.exceptions.WidgetNotFoundException;
import org.miro.widget.helper.DataHelper;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.EmptyStackException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ExceptionAdviceTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @Mock
    private ExceptionAdvice advice;

    @Test
    public void testHandleEmptyStackException() {
        when(advice.handleEmptyStackException(any(EmptyStackException.class)))
        .thenReturn(dataHelper.errorResponseEntity());

        ResponseEntity<ResponseError> response = advice.handleEmptyStackException(dataHelper.emptyStackException());
        assertEquals(dataHelper.errorResponseEntity(), response);
        verify(advice, times(1)).handleEmptyStackException(any(EmptyStackException.class));
    }

    @Test
    public void testHandleReadDataException() {
        when(advice.handleReadDataException(any(ReadDataException.class)))
                .thenReturn(dataHelper.errorResponseEntity());

        ResponseEntity<ResponseError> response = advice.handleReadDataException(dataHelper.readDataException());
        assertEquals(dataHelper.errorResponseEntity(), response);
        verify(advice, times(1)).handleReadDataException(any(ReadDataException.class));
    }

    @Test
    public void testHandleRequestException() {
        when(advice.handleRequestException(any(RequestException.class)))
                .thenReturn(dataHelper.errorResponseEntity());

        ResponseEntity<ResponseError> response = advice.handleRequestException(dataHelper.requestException());
        assertEquals(dataHelper.errorResponseEntity(), response);
        verify(advice, times(1)).handleRequestException(any(RequestException.class));
    }

    @Test
    public void testHandleWidgetNotFoundException() {
        when(advice.handleWidgetNotFoundException(any(WidgetNotFoundException.class)))
                .thenReturn(dataHelper.errorResponseEntity());

        ResponseEntity<ResponseError> response = advice.handleWidgetNotFoundException(dataHelper.widgetNotFoundException());
        assertEquals(dataHelper.errorResponseEntity(), response);
        verify(advice, times(1)).handleWidgetNotFoundException(any(WidgetNotFoundException.class));
    }

    @Test
    public void testHandleWidgetCreateException() {
        when(advice.handleWidgetCreateException(any(WidgetCreateException.class)))
                .thenReturn(dataHelper.errorResponseEntity());

        ResponseEntity<ResponseError> response = advice.handleWidgetCreateException(dataHelper.widgetCreateException());
        assertEquals(dataHelper.errorResponseEntity(), response);
        verify(advice, times(1)).handleWidgetCreateException(any(WidgetCreateException.class));
    }

}
