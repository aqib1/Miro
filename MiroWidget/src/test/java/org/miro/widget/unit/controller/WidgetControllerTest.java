package org.miro.widget.unit.controller;

import org.junit.jupiter.api.Test;
import org.miro.widget.controller.WidgetController;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.response.AllWidgetResponse;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.dto.response.WidgetDeleteResponse;
import org.miro.widget.dto.response.WidgetResponse;
import org.miro.widget.helper.DataHelper;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WidgetControllerTest {
    private final DataHelper dataHelper = DataHelper.getInstance();

    @Mock
    private WidgetController controller;

    @Test
    public void testCreate() {
        when(controller.create(any(WidgetRequest.class))).thenReturn(dataHelper.widgetCreateResponseResponseEntity());
        ResponseEntity<WidgetCreateResponse> response = controller.create(dataHelper.widgetRequest());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dataHelper.widgetCreateResponse(), response.getBody());
        verify(controller, times(1)).create(dataHelper.widgetRequest());
    }

    @Test
    public void testGetAll() {
        when(controller.getAll()).thenReturn(dataHelper.allWidgetResponseEntity());
        ResponseEntity<AllWidgetResponse> response = controller.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dataHelper.allWidgetResponse(), response.getBody());
        verify(controller, times(1)).getAll();
    }

    @Test
    public void testGetById() {
        when(controller.getById(anyString())).thenReturn(dataHelper.widgetResponseEntity());
        ResponseEntity<WidgetResponse> response = controller.getById("test");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dataHelper.widgetResponse(), response.getBody());
        verify(controller, times(1)).getById("test");
    }

    @Test
    public void testUpdateById() {
        when(controller.update(anyString(), any(WidgetRequest.class))).thenReturn(dataHelper.widgetResponseEntity());
        ResponseEntity<WidgetResponse> response = controller.update("test", dataHelper.widgetRequest());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dataHelper.widgetResponse(), response.getBody());
        verify(controller, times(1)).update("test", dataHelper.widgetRequest());
    }

    @Test
    public void testDeleteById() {
        when(controller.delete(anyString())).thenReturn(dataHelper.widgetDeleteResponseEntity());
        ResponseEntity<WidgetDeleteResponse> response = controller.delete(("test"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dataHelper.widgetDeleteResponse(), response.getBody());
        verify(controller, times(1)).delete(("test"));
    }


    @Test
    public void testDeleteAll() {
        when(controller.deleteAll()).thenReturn(dataHelper.widgetDeleteResponseEntity());
        ResponseEntity<WidgetDeleteResponse> response = controller.deleteAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dataHelper.widgetDeleteResponse(), response.getBody());
        verify(controller, times(1)).deleteAll();
    }

    @Test
    public void testGetAllPaging() {
        when(controller.getAll(anyInt(), anyInt())).thenReturn(dataHelper.allWidgetResponseEntity());
        ResponseEntity<AllWidgetResponse> response = controller.getAll(1, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dataHelper.allWidgetResponse(), response.getBody());
        verify(controller, times(1)).getAll(1, 2);
    }
}
