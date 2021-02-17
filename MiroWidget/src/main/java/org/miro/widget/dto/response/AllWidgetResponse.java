package org.miro.widget.dto.response;
import lombok.Builder;
import lombok.Data;
import org.miro.widget.dto.base.Base;

import java.util.List;

@Data
@Builder
public class AllWidgetResponse extends Base {
    private List<WidgetResponse> widgets;
}
