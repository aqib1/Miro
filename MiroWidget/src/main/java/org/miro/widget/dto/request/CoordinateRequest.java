package org.miro.widget.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.miro.widget.dto.base.Base;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class CoordinateRequest extends Base {

    @NotNull(message = "X cannot be null")
    private Integer x;
    @NotNull(message = "Y cannot be null")
    private Integer y;
    @NotNull(message = "Z cannot be null")
    private Integer z;
}
