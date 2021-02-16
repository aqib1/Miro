package org.miro.widget.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Coordinate {
    private Integer x;
    private Integer y;
    private Integer z;
}
