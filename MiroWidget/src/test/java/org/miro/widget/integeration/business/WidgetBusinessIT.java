package org.miro.widget.integeration.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.miro.widget.business.WidgetBusiness;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.response.AllWidgetResponse;
import org.miro.widget.dto.response.WidgetDeleteResponse;
import org.miro.widget.dto.response.WidgetResponse;
import org.miro.widget.exceptions.RequestException;
import org.miro.widget.exceptions.WidgetNotFoundException;
import org.miro.widget.helper.DataHelper;
import org.miro.widget.mapper.MiroWidgetMapper;
import org.miro.widget.mapper.WidgetResponseMapper;
import org.miro.widget.repository.InMemoryWidgetRepository;
import org.miro.widget.service.impl.WidgetServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WidgetBusinessIT {

    private final DataHelper helper = DataHelper.getInstance();

    @Mock
    private InMemoryWidgetRepository repository;

    @Spy
    private WidgetResponseMapper widgetResponseMapper = Mappers.getMapper(WidgetResponseMapper.class);

    @Spy
    private MiroWidgetMapper widgetMapper = Mappers.getMapper(MiroWidgetMapper.class);

    @Spy
    @InjectMocks
    private WidgetServiceImpl service;

    @Spy
    @InjectMocks
    private WidgetBusiness business;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        when(repository.save(any(MiroWidget.class))).thenReturn(true);
        business.create(helper.widgetRequest());
        verify(business, times(1)).create(any(WidgetRequest.class));
        verify(service, times(1)).save(any(MiroWidget.class));
        verify(repository, times(1)).save(any(MiroWidget.class));
    }

    @Test
    public void testGetAll() {
        when(repository.getAll()).thenReturn(helper.miroWidgetList());
        AllWidgetResponse response = business.getAll();
        verify(business, times(1)).getAll();
        verify(service, times(1)).getAll();
        verify(repository, times(1)).getAll();
        assertEquals(helper.miroWidgetList().size(), response.getWidgets().size());
        assertEquals(helper.miroWidgetList().get(0).getHeight(), response.getWidgets().get(0).getHeight());
        assertEquals(helper.miroWidgetList().get(0).getWidth(), response.getWidgets().get(0).getWidth());
        assertEquals(helper.miroWidgetList().get(0).getUuid(), response.getWidgets().get(0).getUuid());
    }

    @Test
    public void testGetById() {
        when(repository.getById(anyString())).thenReturn(helper.miroWidget());
        WidgetResponse response = business.getById(helper.miroWidget().getUuid());
        verify(business, times(1)).getById(anyString());
        verify(service, times(1)).getById(anyString());
        verify(repository, times(1)).getById(anyString());
        assertEquals(helper.miroWidget().getUuid(), response.getUuid());
    }

    @Test
    public void testUpdate() {
        when(repository.update(any(MiroWidget.class))).thenReturn(helper.miroWidget());
        WidgetResponse response = business.update(helper.miroWidget().getUuid(), helper.widgetRequest());
        verify(business, times(1)).update(anyString(), any(WidgetRequest.class));
        verify(service, times(1)).update(anyString(), any(MiroWidget.class));
        verify(repository, times(1)).update(any(MiroWidget.class));
        assertEquals(helper.miroWidget().getUuid(), response.getUuid());
    }

    @Test
    public void testDelete() {
        when(repository.delete(anyString())).thenReturn(true);
        business.delete(helper.miroWidget().getUuid());
        verify(business, times(1)).delete(anyString());
        verify(service, times(1)).delete(anyString());
        verify(repository, times(1)).delete(anyString());
    }

    @Test
    public void testDeleteAll() {
        when(repository.deleteAll()).thenReturn(5);
        WidgetDeleteResponse response = business.deleteAll();
        verify(business, times(1)).deleteAll();
        verify(service, times(1)).deleteAll();
        verify(repository, times(1)).deleteAll();
        assertEquals(5, response.getDeleteWidgets());
    }

    @Test
    public void testCreateInvalidWidth() {
        Assertions.assertThrows(RequestException.class, () -> business.create(helper.invalidWidthWidgetRequest()));
    }

    @Test
    public void testCreateInvalidHeight() {
        Assertions.assertThrows(RequestException.class, () -> business.create(helper.invalidHeightWidgetRequest()));
    }

    @Test
    public void testCreateForNullRequest() {
        Assertions.assertThrows(RequestException.class, () -> business.create(null));
    }

    @Test
    public void testCreateForNullCoordinate() {
        Assertions.assertThrows(RequestException.class, () -> business.create(helper.invalidCoordinateWidgetRequest()));
    }

    @Test
    public void testGetByUnknownId() {
        when(repository.getById(anyString())).thenReturn(null);
        Assertions.assertThrows(WidgetNotFoundException.class, () -> business.getById("Unknown"));
    }

    @Test
    public void testGetByNullId() {
        Assertions.assertThrows(RequestException.class, () -> business.getById(null));
    }

    @Test
    public void testUpdateByNullId() {
        Assertions.assertThrows(RequestException.class, () -> business.update(null, helper.widgetRequest()));
    }

    @Test
    public void testUpdateByNullRequest() {
        Assertions.assertThrows(RequestException.class, () -> business.update("a162587-1uwi-11", null));
    }

    @Test
    public void testUpdateByNullCoordinate() {
        Assertions.assertThrows(RequestException.class, () -> business.update("a162587-1uwi-11", helper.invalidCoordinateWidgetRequest()));
    }

    @Test
    public void testDeleteByNullId() {
        Assertions.assertThrows(RequestException.class, () -> business.delete(null));
    }

    @Test
    public void testUpdateByUnknownId() {
        when(repository.update(any(MiroWidget.class))).thenReturn(null);
        Assertions.assertThrows(WidgetNotFoundException.class, () -> business.update("Unknown", helper.widgetRequest()));
    }

    @Test
    public void testDeleteByUnknownId() {
        when(repository.delete(anyString())).thenReturn(false);
        Assertions.assertThrows(WidgetNotFoundException.class, () -> business.delete("unknown"));
    }

    @Test
    public void testDeleteAllWhenDataEmpty() {
        when(repository.deleteAll()).thenReturn(0);
        Assertions.assertThrows(EmptyStackException.class, () -> business.deleteAll());
    }
}
