package org.miro.widget.mapper;

import org.mapstruct.Mapper;
import org.miro.widget.domain.Coordinate;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.request.CoordinateRequest;
import org.miro.widget.dto.request.WidgetRequest;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MiroWidgetMapper {

    MiroWidget miroWidgetFromWidgetRequest(WidgetRequest widgetRequest);

    List<MiroWidget> miroWidgetListFromWidgetRequestList(List<WidgetRequest> widgetRequestList);

    Coordinate coordinateFromCoordinateRequest(CoordinateRequest request);

    List<Coordinate> coordinateListFromCoordinateRequestList(List<CoordinateRequest> coordinateRequestList);
}
