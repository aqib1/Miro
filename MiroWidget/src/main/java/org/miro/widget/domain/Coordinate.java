package org.miro.widget.domain;

import lombok.Builder;
import lombok.Data;
import org.miro.widget.dto.base.Base;

@Data
@Builder
public class Coordinate extends Base {
    private Integer x;
    private Integer y;
    private Integer z;
}
