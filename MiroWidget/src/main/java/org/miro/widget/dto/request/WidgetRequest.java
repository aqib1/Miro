package org.miro.widget.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class WidgetRequest {

    @NotNull(message = "Width must not be null")
    private Double width;

    @NotNull(message = "Height must not be null")
    private Double height;

    @Valid
    @NotNull(message = "Coordinates must not be null")
    private CoordinateRequest coordinateRequest;
}
