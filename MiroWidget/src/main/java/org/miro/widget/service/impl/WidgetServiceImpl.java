package org.miro.widget.service.impl;

import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.exceptions.WidgetOfferException;
import org.miro.widget.repository.InMemoryWidgetRepository;
import org.miro.widget.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.miro.widget.helper.AppUtils.getUUID;

@Service
public class WidgetServiceImpl implements WidgetService {

    @Autowired
    private InMemoryWidgetRepository repository;

    @Override
    public WidgetCreateResponse save(MiroWidget widget) {
        String uuid = getUUID();
        widget.setUuid(uuid);
        return Optional.of(repository.save(widget))
                .filter(i -> i == Boolean.TRUE)
                .map(i -> WidgetCreateResponse.builder()
                        .uuid(uuid)
                        .message("Widget created successfully!! uuid = " + uuid)
                        .build())
                .orElseThrow(() -> new WidgetOfferException("Widget failed to create!"));
    }
}
