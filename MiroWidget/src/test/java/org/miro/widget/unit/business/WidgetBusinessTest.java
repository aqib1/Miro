package org.miro.widget.unit.business;

import org.junit.jupiter.api.Test;
import org.miro.widget.business.WidgetBusiness;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.response.AllWidgetResponse;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.dto.response.WidgetDeleteResponse;
import org.miro.widget.dto.response.WidgetResponse;
import org.miro.widget.helper.DataHelper;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WidgetBusinessTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @Mock
    private WidgetBusiness business;

    @Test
    public void testCreate() {
        when(business.create(any(WidgetRequest.class))).thenReturn(dataHelper.widgetCreateResponse());
        WidgetCreateResponse response = business.create(dataHelper.widgetRequest());
        assertEquals(dataHelper.widgetCreateResponse(), response);
        verify(business, times(1)).create(dataHelper.widgetRequest());
    }

    @Test
    public void testGetAll() {
        when(business.getAll()).thenReturn(dataHelper.allWidgetResponse());
        AllWidgetResponse response = business.getAll();
        assertEquals(dataHelper.allWidgetResponse(), response);
        verify(business, times(1)).getAll();
    }

    @Test
    public void testGetById() {
        when(business.getById(anyString())).thenReturn(dataHelper.widgetResponse());
        WidgetResponse response = business.getById("test");
        assertEquals(dataHelper.widgetResponse(), response);
        verify(business, times(1)).getById("test");
    }

    @Test
    public void testUpdateById() {
        when(business.update(anyString(), any(WidgetRequest.class))).thenReturn(dataHelper.widgetResponse());
        WidgetResponse response = business.update("test", dataHelper.widgetRequest());
        assertEquals(dataHelper.widgetResponse(), response);
        verify(business, times(1)).update("test", dataHelper.widgetRequest());
    }

    @Test
    public void testDeleteById() {
        when(business.delete(anyString())).thenReturn(dataHelper.widgetDeleteResponse());
        WidgetDeleteResponse response = business.delete(("test"));
        assertEquals(dataHelper.widgetDeleteResponse(), response);
        verify(business, times(1)).delete(("test"));
    }


    @Test
    public void testDeleteAll() {
        when(business.deleteAll()).thenReturn(dataHelper.widgetDeleteResponse());
        WidgetDeleteResponse response = business.deleteAll();
        assertEquals(dataHelper.widgetDeleteResponse(), response);
        verify(business, times(1)).deleteAll();
    }

    @Test
    public void testGetAllPaging() {
        when(business.getAll(anyInt(), anyInt())).thenReturn(dataHelper.allWidgetResponse());
        AllWidgetResponse response = business.getAll(1, 2);
        assertEquals(dataHelper.allWidgetResponse(), response);
        verify(business, times(1)).getAll(1, 2);
    }
}
