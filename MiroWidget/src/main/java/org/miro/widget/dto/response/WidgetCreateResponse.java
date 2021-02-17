package org.miro.widget.dto.response;

import lombok.*;
import org.miro.widget.dto.base.Base;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WidgetCreateResponse extends Base {
    private String uuid;
    private String message;
}
