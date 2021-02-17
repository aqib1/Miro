package org.miro.widget.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.miro.widget.domain.Coordinate;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.response.CoordinateResponse;
import org.miro.widget.dto.response.WidgetResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-16T13:58:16+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class WidgetResponseMapperImpl implements WidgetResponseMapper {

    @Override
    public WidgetResponse widgetResponseFromMiroWidget(MiroWidget widget) {
        if ( widget == null ) {
            return null;
        }

        WidgetResponse widgetResponse = new WidgetResponse();

        widgetResponse.setResponse( coordinateToCoordinateResponse( widget.getCoordinate() ) );
        widgetResponse.setUuid( widget.getUuid() );
        widgetResponse.setWidth( widget.getWidth() );
        widgetResponse.setHeight( widget.getHeight() );
        widgetResponse.setLastModificationDate( widget.getLastModificationDate() );

        return widgetResponse;
    }

    @Override
    public List<WidgetResponse> widgetResponseListFromMiroWidgetList(List<MiroWidget> miroWidgetList) {
        if ( miroWidgetList == null ) {
            return null;
        }

        List<WidgetResponse> list = new ArrayList<WidgetResponse>( miroWidgetList.size() );
        for ( MiroWidget miroWidget : miroWidgetList ) {
            list.add( widgetResponseFromMiroWidget( miroWidget ) );
        }

        return list;
    }

    protected CoordinateResponse coordinateToCoordinateResponse(Coordinate coordinate) {
        if ( coordinate == null ) {
            return null;
        }

        CoordinateResponse coordinateResponse = new CoordinateResponse();

        coordinateResponse.setX( coordinate.getX() );
        coordinateResponse.setY( coordinate.getY() );
        coordinateResponse.setZ( coordinate.getZ() );

        return coordinateResponse;
    }
}
