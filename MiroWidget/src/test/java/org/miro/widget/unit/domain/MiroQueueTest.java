package org.miro.widget.unit.domain;

import org.junit.jupiter.api.Test;
import org.miro.widget.domain.MiroQueue;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.helper.DataHelper;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MiroQueueTest {
    private final DataHelper helper = DataHelper.getInstance();

    @Mock
    private MiroQueue miroQueue;

    @Test
    public void testOffer() {
        when(miroQueue.offer(any(MiroWidget.class))).thenReturn(true);
        boolean response = miroQueue.offer(helper.miroWidget());
        verify(miroQueue, times(1)).offer(any(MiroWidget.class));
        assertEquals(Boolean.TRUE, response);
    }

    @Test
    public void testGetAll() {
        when(miroQueue.getAll()).thenReturn(helper.miroWidgetList());
        List<MiroWidget> widgets = miroQueue.getAll();
        verify(miroQueue, times(1)).getAll();
        assertEquals(helper.miroWidgetList().size(), widgets.size());
        assertEquals(helper.miroWidgetList().get(0).getHeight(), widgets.get(0).getHeight());
        assertEquals(helper.miroWidgetList().get(0).getWidth(), widgets.get(0).getWidth());
        assertEquals(helper.miroWidgetList().get(0).getUuid(), widgets.get(0).getUuid());
    }

    @Test
    public void testGetById() {
        when(miroQueue.getById(anyString())).thenReturn(helper.miroWidget());
        MiroWidget response = miroQueue.getById(helper.miroWidget().getUuid());
        verify(miroQueue, times(1)).getById(anyString());
        assertEquals(helper.miroWidget().getUuid(), response.getUuid());
    }

    @Test
    public void testUpdate() {
        when(miroQueue.update(any(MiroWidget.class))).thenReturn(helper.miroWidget());
        MiroWidget response = miroQueue.update(helper.miroWidget());
        verify(miroQueue, times(1)).update(any(MiroWidget.class));
        assertEquals(helper.miroWidget().getUuid(), response.getUuid());
    }

    @Test
    public void testDelete() {
        when(miroQueue.delete(anyString())).thenReturn(true);
        miroQueue.delete(helper.miroWidget().getUuid());
        verify(miroQueue, times(1)).delete(anyString());
    }

    @Test
    public void testDeleteAll() {
        when(miroQueue.deleteAll()).thenReturn(5);
        int response = miroQueue.deleteAll();
        verify(miroQueue, times(1)).deleteAll();
        assertEquals(5, response);
    }
}
