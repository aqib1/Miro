package org.miro.widget.dto.response;
import lombok.Builder;
import lombok.Data;
import org.miro.widget.dto.base.Base;

@Data
@Builder
public class WidgetDeleteResponse extends Base {
    private int deleteWidgets;
    private String message;
}
