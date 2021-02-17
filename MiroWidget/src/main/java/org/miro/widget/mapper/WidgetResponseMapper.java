package org.miro.widget.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.response.WidgetResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WidgetResponseMapper {

    @Mapping(target="coordinateResponse", source="coordinate")
    WidgetResponse widgetResponseFromMiroWidget(MiroWidget widget);
    List<WidgetResponse> widgetResponseListFromMiroWidgetList(List<MiroWidget> miroWidgetList);
}
