package org.miro.widget.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class CoordinateRequest {

    @NotNull(message = "X cannot be null")
    private Integer x;
    @NotNull(message = "Y cannot be null")
    private Integer y;
    @NotNull(message = "Z cannot be null")
    private Integer z;
}
