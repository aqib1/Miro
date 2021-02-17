package org.miro.widget.service;

import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.response.AllWidgetResponse;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.dto.response.WidgetDeleteResponse;
import org.miro.widget.dto.response.WidgetResponse;

public interface WidgetService {
    WidgetCreateResponse save(MiroWidget widget);
    AllWidgetResponse getAll();
    WidgetResponse getById(String uuid);
    WidgetResponse update(String uuid, MiroWidget widget);
    WidgetDeleteResponse delete(String uuid);
}
