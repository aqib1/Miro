package org.miro.widget.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.miro.widget.domain.Coordinate;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.request.CoordinateRequest;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.request.WidgetUpdateRequest;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-16T13:58:46+0100",
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

        miroWidget.setCoordinate( coordinateFromCoordinateRequest( widgetRequest.getCoordinateRequest() ) );
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

        Coordinate coordinate = new Coordinate();

        coordinate.setX( request.getX() );
        coordinate.setY( request.getY() );
        coordinate.setZ( request.getZ() );

        return coordinate;
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

    @Override
    public MiroWidget miroWidgetFromWidgetUpdateRequest(WidgetUpdateRequest request) {
        if ( request == null ) {
            return null;
        }

        MiroWidget miroWidget = new MiroWidget();

        miroWidget.setWidth( requestRequestWidth( request ) );
        miroWidget.setHeight( requestRequestHeight( request ) );
        miroWidget.setCoordinate( coordinateFromCoordinateRequest( requestRequestCoordinateRequest( request ) ) );
        miroWidget.setUuid( request.getUuid() );

        return miroWidget;
    }

    private Double requestRequestWidth(WidgetUpdateRequest widgetUpdateRequest) {
        if ( widgetUpdateRequest == null ) {
            return null;
        }
        WidgetRequest request = widgetUpdateRequest.getRequest();
        if ( request == null ) {
            return null;
        }
        Double width = request.getWidth();
        if ( width == null ) {
            return null;
        }
        return width;
    }

    private Double requestRequestHeight(WidgetUpdateRequest widgetUpdateRequest) {
        if ( widgetUpdateRequest == null ) {
            return null;
        }
        WidgetRequest request = widgetUpdateRequest.getRequest();
        if ( request == null ) {
            return null;
        }
        Double height = request.getHeight();
        if ( height == null ) {
            return null;
        }
        return height;
    }

    private CoordinateRequest requestRequestCoordinateRequest(WidgetUpdateRequest widgetUpdateRequest) {
        if ( widgetUpdateRequest == null ) {
            return null;
        }
        WidgetRequest request = widgetUpdateRequest.getRequest();
        if ( request == null ) {
            return null;
        }
        CoordinateRequest coordinateRequest = request.getCoordinateRequest();
        if ( coordinateRequest == null ) {
            return null;
        }
        return coordinateRequest;
    }
}
