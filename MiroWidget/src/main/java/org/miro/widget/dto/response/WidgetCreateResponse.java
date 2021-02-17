package org.miro.widget.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WidgetCreateResponse {
    private String uuid;
    private String message;
}
