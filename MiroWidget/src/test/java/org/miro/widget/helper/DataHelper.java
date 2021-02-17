package org.miro.widget.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.miro.widget.domain.Coordinate;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.request.CoordinateRequest;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.response.*;
import org.miro.widget.exceptions.ReadDataException;
import org.miro.widget.exceptions.RequestException;
import org.miro.widget.exceptions.WidgetCreateException;
import org.miro.widget.exceptions.WidgetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

public class DataHelper {

    public ResponseEntity<ResponseError> errorResponseEntity() {
        return new ResponseEntity<>(responseError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<AllWidgetResponse> allWidgetResponseEntity() {
        return ResponseEntity.ok(allWidgetResponse());
    }

    public ResponseEntity<WidgetCreateResponse> widgetCreateResponseResponseEntity() {
        return ResponseEntity.ok(widgetCreateResponse());
    }

    public ResponseEntity<WidgetResponse> widgetResponseEntity() {
        return ResponseEntity.ok(widgetResponse());
    }

    public ResponseEntity<WidgetDeleteResponse> widgetDeleteResponseEntity() {
        return ResponseEntity.ok(widgetDeleteResponse());
    }

    public EmptyStackException emptyStackException() {
        return new EmptyStackException();
    }

    public ReadDataException readDataException() {
        return new ReadDataException();
    }

    public RequestException requestException() {
        return new RequestException();
    }

    public WidgetNotFoundException widgetNotFoundException() {
        return new WidgetNotFoundException();
    }

    public WidgetCreateException widgetCreateException() {
        return new WidgetCreateException();
    }

    public List<WidgetRequest> widgetRequestList() {
        return Arrays.asList(widgetRequest());
    }

    public WidgetDeleteResponse widgetDeleteResponse() {
        return WidgetDeleteResponse.builder()
                .deleteWidgets(1)
                .message("Widget successfully deleted")
                .build();
    }

    public AllWidgetResponse allWidgetResponse() {
        return AllWidgetResponse.builder()
                .widgets(widgetResponseList())
                .build();
    }

    public List<WidgetResponse> widgetResponseList() {
        return Arrays.asList(widgetResponse());
    }

    public WidgetResponse widgetResponse() {
        return WidgetResponse.builder()
                .height(100d)
                .width(100d)
                .coordinateResponse(CoordinateResponse.builder()
                        .x(1)
                        .y(2)
                        .z(3)
                        .build())
                .build();
    }

    public WidgetCreateResponse widgetCreateResponse() {
        return WidgetCreateResponse.builder()
                .uuid("143165-198769-190810")
                .message("widget created successfully")
                .build();
    }

    public WidgetRequest invalidCoordinateWidgetRequest() {
        return WidgetRequest.builder()
                .width(100d)
                .height(-100d)
                .build();
    }

    public WidgetRequest invalidHeightWidgetRequest() {
        return WidgetRequest.builder()
                .width(100d)
                .height(-100d)
                .coordinateRequest(coordinateRequest())
                .build();
    }

    public WidgetRequest invalidWidthWidgetRequest() {
        return WidgetRequest.builder()
                .width(-100d)
                .height(100d)
                .coordinateRequest(coordinateRequest())
                .build();
    }

    public List<CoordinateRequest> coordinateRequestList() {
        return Arrays.asList(coordinateRequest());
    }

    public CoordinateRequest coordinateRequest() {
        return CoordinateRequest.builder()
                .x(3)
                .y(1)
                .z(2)
                .build();
    }

    public WidgetRequest widgetRequest() {
        return WidgetRequest.builder()
                .width(100d)
                .height(100d)
                .coordinateRequest(coordinateRequest())
                .build();
    }

    public MiroWidget miroWidget() {
        return MiroWidget.builder()
                .uuid("1521gut-29872-11762")
                .height(100d)
                .width(100d)
                .coordinate(Coordinate.builder()
                        .x(3)
                        .y(1)
                        .z(2)
                        .build())
                .build();
    }

    public List<MiroWidget> miroWidgetList() {
        return Arrays.asList(miroWidget());
    }

    public List<Coordinate> coordinateList() {
        return Arrays.asList(coordinate());
    }

    public Coordinate coordinate() {
        return Coordinate.builder()
                .x(1)
                .y(2)
                .z(3)
                .build();
    }

    public ResponseError responseError() {
        return ResponseError.builder()
                .errorCode(1)
                .errorMessage("")
                .createdAt("")
                .detailedMessage("")
                .exceptionName("")
                .build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T asObjectFromJsonString(final String value, final Class<T> c) {
        try {
            return new ObjectMapper().readValue(value, c);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Initialization on demand pattern
     * this pattern is alternative of double check locking pattern
     * which not even support lazy loading but also safe to use in
     * multi-processor distributed instances
     */

    private static class InstanceHolder {
        private static final DataHelper INSTANCE = new DataHelper();

        private InstanceHolder() {

        }
    }

    public static DataHelper getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private DataHelper() {

    }
}
