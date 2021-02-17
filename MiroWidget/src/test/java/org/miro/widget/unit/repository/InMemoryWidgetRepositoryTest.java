package org.miro.widget.unit.repository;

import org.junit.jupiter.api.Test;
import org.miro.widget.domain.MiroWidget;
import java.util.List;
import org.miro.widget.helper.DataHelper;
import org.miro.widget.repository.InMemoryWidgetRepository;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InMemoryWidgetRepositoryTest {
    private final DataHelper dataHelper = DataHelper.getInstance();
    @Mock
    private InMemoryWidgetRepository repository;

    @Test
    public void testSave() {
        when(repository.save(any(MiroWidget.class))).thenReturn(true);
        boolean response = repository.save(dataHelper.miroWidget());
        assertEquals(Boolean.TRUE, response);
        verify(repository, times(1)).save(dataHelper.miroWidget());
    }

    @Test
    public void testGetAll() {
        when(repository.getAll()).thenReturn(dataHelper.miroWidgetList());
        List<MiroWidget> response = repository.getAll();
        assertEquals(dataHelper.miroWidgetList(), response);
        verify(repository, times(1)).getAll();
    }

    @Test
    public void testGetById() {
        when(repository.getById(anyString())).thenReturn(dataHelper.miroWidget());
        MiroWidget response = repository.getById("test");
        assertEquals(dataHelper.miroWidget(), response);
        verify(repository, times(1)).getById("test");
    }

    @Test
    public void testUpdateById() {
        when(repository.update(any(MiroWidget.class))).thenReturn(dataHelper.miroWidget());
        MiroWidget response = repository.update(dataHelper.miroWidget());
        assertEquals(dataHelper.miroWidget(), response);
        verify(repository, times(1)).update(dataHelper.miroWidget());
    }

    @Test
    public void testDeleteById() {
        when(repository.delete(anyString())).thenReturn(true);
        boolean response = repository.delete(("test"));
        assertEquals(Boolean.TRUE, response);
        verify(repository, times(1)).delete(("test"));
    }

    @Test
    public void testDeleteAll() {
        when(repository.deleteAll()).thenReturn(5);
        int response = repository.deleteAll();
        assertEquals(5, response);
        verify(repository, times(1)).deleteAll();
    }
}
