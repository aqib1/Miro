package org.miro.widget.domain;

import lombok.Builder;
import lombok.Data;
import org.miro.widget.dto.base.Base;


@Data
@Builder
public class MiroWidget extends Base {
    private String uuid;
    private Double width;
    private Double height;
    private Coordinate coordinate;
    private String lastModificationDate;
}
