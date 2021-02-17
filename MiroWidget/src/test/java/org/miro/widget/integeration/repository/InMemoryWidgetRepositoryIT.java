package org.miro.widget.integeration.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.miro.widget.domain.MiroQueue;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.helper.DataHelper;
import org.miro.widget.repository.InMemoryWidgetRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InMemoryWidgetRepositoryIT {

    private final DataHelper helper = DataHelper.getInstance();

    @Mock
    private MiroQueue miroQueue;

    @Spy @InjectMocks
    private InMemoryWidgetRepository repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        when(miroQueue.offer(any(MiroWidget.class))).thenReturn(true);
        repository.save(helper.miroWidget());
        verify(repository, times(1)).save(any(MiroWidget.class));
        verify(miroQueue, times(1)).offer(any(MiroWidget.class));
    }

    @Test
    public void testGetAll() {
        when(miroQueue.getAll()).thenReturn(helper.miroWidgetList());
        List<MiroWidget> widgets = repository.getAll();
        verify(repository, times(1)).getAll();
        verify(miroQueue, times(1)).getAll();
        assertEquals(helper.miroWidgetList().size(), widgets.size());
        assertEquals(helper.miroWidgetList().get(0).getHeight(), widgets.get(0).getHeight());
        assertEquals(helper.miroWidgetList().get(0).getWidth(), widgets.get(0).getWidth());
        assertEquals(helper.miroWidgetList().get(0).getUuid(), widgets.get(0).getUuid());
    }

    @Test
    public void testGetById() {
        when(miroQueue.getById(anyString())).thenReturn(helper.miroWidget());
        MiroWidget response = repository.getById(helper.miroWidget().getUuid());
        verify(repository, times(1)).getById(anyString());
        verify(miroQueue, times(1)).getById(anyString());
        assertEquals(helper.miroWidget().getUuid(), response.getUuid());
    }

    @Test
    public void testUpdate() {
        when(miroQueue.update(any(MiroWidget.class))).thenReturn(helper.miroWidget());
        MiroWidget response = repository.update(helper.miroWidget());
        verify(repository, times(1)).update(any(MiroWidget.class));
        verify(miroQueue, times(1)).update(any(MiroWidget.class));
        assertEquals(helper.miroWidget().getUuid(), response.getUuid());
    }

    @Test
    public void testDelete() {
        when(miroQueue.delete(anyString())).thenReturn(true);
        repository.delete(helper.miroWidget().getUuid());
        verify(repository, times(1)).delete(anyString());
        verify(miroQueue, times(1)).delete(anyString());
    }

    @Test
    public void testDeleteAll() {
        when(miroQueue.deleteAll()).thenReturn(5);
        int response = repository.deleteAll();
        verify(repository, times(1)).deleteAll();
        verify(miroQueue, times(1)).deleteAll();
        assertEquals(5, response);
    }
}
