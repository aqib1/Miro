package org.miro.widget.integeration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.miro.widget.domain.MiroWidget;
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

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WidgetServiceImplIT {

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

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        when(repository.save(any(MiroWidget.class))).thenReturn(true);
        service.save(helper.miroWidget());
        verify(service, times(1)).save(any(MiroWidget.class));
        verify(repository, times(1)).save(any(MiroWidget.class));
    }

    @Test
    public void testGetAll() {
        when(repository.getAll()).thenReturn(helper.miroWidgetList());
        AllWidgetResponse response = service.getAll();
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
        WidgetResponse response = service.getById(helper.miroWidget().getUuid());
        verify(service, times(1)).getById(anyString());
        verify(repository, times(1)).getById(anyString());
        assertEquals(helper.miroWidget().getUuid(), response.getUuid());
    }

    @Test
    public void testUpdate() {
        when(repository.update(any(MiroWidget.class))).thenReturn(helper.miroWidget());
        WidgetResponse response = service.update(helper.miroWidget().getUuid(), helper.miroWidget());
        verify(service, times(1)).update(anyString(), any(MiroWidget.class));
        verify(repository, times(1)).update(any(MiroWidget.class));
        assertEquals(helper.miroWidget().getUuid(), response.getUuid());
    }

    @Test
    public void testDelete() {
        when(repository.delete(anyString())).thenReturn(true);
        service.delete(helper.miroWidget().getUuid());
        verify(service, times(1)).delete(anyString());
        verify(repository, times(1)).delete(anyString());
    }

    @Test
    public void testDeleteAll() {
        when(repository.deleteAll()).thenReturn(5);
        WidgetDeleteResponse response = service.deleteAll();
        verify(service, times(1)).deleteAll();
        verify(repository, times(1)).deleteAll();
        assertEquals(5, response.getDeleteWidgets());
    }

    @Test
    public void testGetByUnknownId() {
        when(repository.getById(anyString())).thenReturn(null);
        Assertions.assertThrows(WidgetNotFoundException.class, () -> service.getById("unknown"));
    }

    @Test
    public void testUpdateByUnknownId() {
        when(repository.update(any(MiroWidget.class))).thenReturn(null);
        Assertions.assertThrows(WidgetNotFoundException.class, () -> service.update("unknown", helper.miroWidget()));
    }

    @Test
    public void testDeleteByUnknownId() {
        when(repository.delete(anyString())).thenReturn(false);
        Assertions.assertThrows(WidgetNotFoundException.class, () -> service.delete("unknown"));
    }

    @Test
    public void testDeleteAllWhenDataEmpty() {
        when(repository.deleteAll()).thenReturn(0);
        Assertions.assertThrows(EmptyStackException.class, () -> service.deleteAll());
    }
}
