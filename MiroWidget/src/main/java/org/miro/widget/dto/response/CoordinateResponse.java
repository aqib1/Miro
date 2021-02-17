package org.miro.widget.dto.response;

import lombok.Builder;
import lombok.Data;
import org.miro.widget.dto.base.Base;

@Data
@Builder
public class CoordinateResponse extends Base {
    private Integer x;
    private Integer y;
    private Integer z;
}
