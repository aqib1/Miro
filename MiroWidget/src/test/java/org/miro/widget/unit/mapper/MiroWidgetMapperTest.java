package org.miro.widget.unit.mapper;

import org.junit.jupiter.api.Test;
import org.miro.widget.domain.Coordinate;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.request.CoordinateRequest;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.helper.DataHelper;
import org.miro.widget.mapper.MiroWidgetMapper;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MiroWidgetMapperTest {
    private final DataHelper dataHelper = DataHelper.getInstance();
    @Mock
    private MiroWidgetMapper mapper;

    @Test
    public void miroWidgetFromWidgetRequest() {
        when(mapper.miroWidgetFromWidgetRequest(any(WidgetRequest.class)))
        .thenReturn(dataHelper.miroWidget());
        MiroWidget widget = mapper.miroWidgetFromWidgetRequest(dataHelper.widgetRequest());
        assertEquals(dataHelper.miroWidget(), widget);
        verify(mapper, times(1)).miroWidgetFromWidgetRequest(dataHelper.widgetRequest());
    }

    @Test
    public void miroWidgetListFromWidgetRequestList() {
        when(mapper.miroWidgetListFromWidgetRequestList(anyList()))
                .thenReturn(dataHelper.miroWidgetList());
        List<MiroWidget> widgets = mapper.miroWidgetListFromWidgetRequestList(dataHelper.widgetRequestList());
        assertEquals(dataHelper.miroWidgetList(), widgets);
        verify(mapper, times(1)).miroWidgetListFromWidgetRequestList(dataHelper.widgetRequestList());
    }

    @Test
    public void coordinateFromCoordinateRequest() {
        when(mapper.coordinateFromCoordinateRequest(any(CoordinateRequest.class)))
                .thenReturn(dataHelper.coordinate());
        Coordinate coordinate = mapper.coordinateFromCoordinateRequest(dataHelper.coordinateRequest());
        assertEquals(dataHelper.coordinate(), coordinate);
        verify(mapper, times(1)).coordinateFromCoordinateRequest(dataHelper.coordinateRequest());
    }

    @Test
    public void coordinateListFromCoordinateRequestList() {
        when(mapper.coordinateListFromCoordinateRequestList(any(List.class)))
                .thenReturn(dataHelper.coordinateList());
        List<Coordinate> coordinates = mapper.coordinateListFromCoordinateRequestList(dataHelper.coordinateRequestList());
        assertEquals(dataHelper.coordinateList(), coordinates);
        verify(mapper, times(1)).coordinateListFromCoordinateRequestList(dataHelper.coordinateRequestList());
    }
}
