package org.miro.widget.dto.response;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class AllWidgetResponse {
    private List<WidgetResponse> widgets;
}
