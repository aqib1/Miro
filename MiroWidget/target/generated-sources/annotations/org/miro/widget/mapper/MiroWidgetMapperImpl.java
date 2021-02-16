package org.miro.widget.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.miro.widget.domain.Coordinate;
import org.miro.widget.domain.Coordinate.CoordinateBuilder;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.request.CoordinateRequest;
import org.miro.widget.dto.request.WidgetRequest;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-16T02:48:34+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class MiroWidgetMapperImpl implements MiroWidgetMapper {

    @Override
    public MiroWidget miroWidgetFromWidgetRequest(WidgetRequest widgetRequest) {
        if ( widgetRequest == null ) {
            return null;
        }

        MiroWidget miroWidget = new MiroWidget();

        miroWidget.setWidth( widgetRequest.getWidth() );
        miroWidget.setHeight( widgetRequest.getHeight() );

        return miroWidget;
    }

    @Override
    public List<MiroWidget> miroWidgetListFromWidgetRequestList(List<WidgetRequest> widgetRequestList) {
        if ( widgetRequestList == null ) {
            return null;
        }

        List<MiroWidget> list = new ArrayList<MiroWidget>( widgetRequestList.size() );
        for ( WidgetRequest widgetRequest : widgetRequestList ) {
            list.add( miroWidgetFromWidgetRequest( widgetRequest ) );
        }

        return list;
    }

    @Override
    public Coordinate coordinateFromCoordinateRequest(CoordinateRequest request) {
        if ( request == null ) {
            return null;
        }

        CoordinateBuilder coordinate = Coordinate.builder();

        coordinate.x( request.getX() );
        coordinate.y( request.getY() );
        coordinate.z( request.getZ() );

        return coordinate.build();
    }

    @Override
    public List<Coordinate> coordinateListFromCoordinateRequestList(List<CoordinateRequest> coordinateRequestList) {
        if ( coordinateRequestList == null ) {
            return null;
        }

        List<Coordinate> list = new ArrayList<Coordinate>( coordinateRequestList.size() );
        for ( CoordinateRequest coordinateRequest : coordinateRequestList ) {
            list.add( coordinateFromCoordinateRequest( coordinateRequest ) );
        }

        return list;
    }
}
