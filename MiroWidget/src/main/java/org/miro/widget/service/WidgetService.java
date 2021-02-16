package org.miro.widget.service;

import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.response.WidgetCreateResponse;

public interface WidgetService {

    WidgetCreateResponse save(MiroWidget widget);
}
