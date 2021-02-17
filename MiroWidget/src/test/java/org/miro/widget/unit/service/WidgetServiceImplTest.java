package org.miro.widget.unit.service;

import org.junit.jupiter.api.Test;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.response.AllWidgetResponse;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.dto.response.WidgetDeleteResponse;
import org.miro.widget.dto.response.WidgetResponse;
import org.miro.widget.helper.DataHelper;
import org.miro.widget.service.impl.WidgetServiceImpl;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WidgetServiceImplTest {
    private final DataHelper dataHelper = DataHelper.getInstance();

    @Mock
    private WidgetServiceImpl service;

    @Test
    public void testCreate() {
        when(service.save(any(MiroWidget.class))).thenReturn(dataHelper.widgetCreateResponse());
        WidgetCreateResponse response = service.save(dataHelper.miroWidget());
        assertEquals(dataHelper.widgetCreateResponse(), response);
        verify(service, times(1)).save(dataHelper.miroWidget());
    }

    @Test
    public void testGetAll() {
        when(service.getAll()).thenReturn(dataHelper.allWidgetResponse());
        AllWidgetResponse response = service.getAll();
        assertEquals(dataHelper.allWidgetResponse(), response);
        verify(service, times(1)).getAll();
    }

    @Test
    public void testGetById() {
        when(service.getById(anyString())).thenReturn(dataHelper.widgetResponse());
        WidgetResponse response = service.getById("test");
        assertEquals(dataHelper.widgetResponse(), response);
        verify(service, times(1)).getById("test");
    }

    @Test
    public void testUpdateById() {
        when(service.update(anyString(), any(MiroWidget.class))).thenReturn(dataHelper.widgetResponse());
        WidgetResponse response = service.update("test", dataHelper.miroWidget());
        assertEquals(dataHelper.widgetResponse(), response);
        verify(service, times(1)).update("test", dataHelper.miroWidget());
    }

    @Test
    public void testDeleteById() {
        when(service.delete(anyString())).thenReturn(dataHelper.widgetDeleteResponse());
        WidgetDeleteResponse response = service.delete(("test"));
        assertEquals(dataHelper.widgetDeleteResponse(), response);
        verify(service, times(1)).delete(("test"));
    }

    @Test
    public void testDeleteAll() {
        when(service.deleteAll()).thenReturn(dataHelper.widgetDeleteResponse());
        WidgetDeleteResponse response = service.deleteAll();
        assertEquals(dataHelper.widgetDeleteResponse(), response);
        verify(service, times(1)).deleteAll();
    }

    @Test
    public void testGetAllPaging() {
        when(service.getAll(anyInt(), anyInt())).thenReturn(dataHelper.allWidgetResponse());
        AllWidgetResponse response = service.getAll(1, 2);
        assertEquals(dataHelper.allWidgetResponse(), response);
        verify(service, times(1)).getAll(1, 2);
    }
}
