package org.miro.widget.dto.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WidgetDeleteResponse {
    private int deleteWidgets;
    private String message;
}
