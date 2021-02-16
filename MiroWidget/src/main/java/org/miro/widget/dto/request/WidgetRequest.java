package org.miro.widget.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@Getter
public class WidgetRequest {

    @NotNull(message = "Width must not be null")
    private Double width;

    @NotNull(message = "Height must not be null")
    private Double height;

    @Valid
    @NotNull(message = "Coordinates must not be null")
    private CoordinateRequest coordinateRequest;
}
