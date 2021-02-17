package org.miro.widget.dto.response;

import lombok.Data;

@Data
public class WidgetResponse {
    private String uuid;
    private Double width;
    private Double height;
    private String lastModificationDate;
    private String detail;
    private CoordinateResponse response;
}
