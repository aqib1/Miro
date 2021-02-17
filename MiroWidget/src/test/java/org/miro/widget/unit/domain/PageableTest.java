package org.miro.widget.unit.domain;

import org.junit.jupiter.api.Test;
import org.miro.widget.domain.Pageable;
import org.miro.widget.helper.DataHelper;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PageableTest {
    private DataHelper dataHelper = DataHelper.getInstance();
    @Mock
    private Pageable pageable;

    @Test
    public void testCalculatePages() {
        when(pageable.getContent()).thenReturn(dataHelper.widgetResponseList());
        pageable.getContent();
        verify(pageable, times(1)).getContent();
    }

    @Test
    public void testSetPageSize() {
        doNothing().when(pageable).setPageSize(anyInt());
        pageable.setPageSize(11);
        verify(pageable, times(1)).setPageSize(anyInt());
    }

    @Test
    public void testSetPage() {
        doNothing().when(pageable).setPage(anyInt());
        pageable.setPage(11);
        verify(pageable, times(1)).setPage(anyInt());
    }
}
