package org.miro.widget.dto.response;

import lombok.Builder;
import lombok.Data;
import org.miro.widget.dto.base.Base;

@Builder
@Data
public class WidgetResponse extends Base {
    private String uuid;
    private Double width;
    private Double height;
    private String lastModificationDate;
    private String detail;
    private CoordinateResponse coordinateResponse;
}
