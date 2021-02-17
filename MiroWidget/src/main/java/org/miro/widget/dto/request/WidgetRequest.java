package org.miro.widget.dto.request;

import lombok.Builder;
import lombok.Data;
import org.miro.widget.dto.base.Base;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class WidgetRequest extends Base {

    @NotNull(message = "Width must not be null")
    private Double width;

    @NotNull(message = "Height must not be null")
    private Double height;

    @Valid
    @NotNull(message = "Coordinates must not be null")
    private CoordinateRequest coordinateRequest;
}
