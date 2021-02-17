package org.miro.widget.service.impl;

import org.miro.widget.domain.MiroWidget;
import org.miro.widget.domain.Pageable;
import org.miro.widget.dto.response.AllWidgetResponse;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.dto.response.WidgetDeleteResponse;
import org.miro.widget.dto.response.WidgetResponse;
import org.miro.widget.exceptions.ReadDataException;
import org.miro.widget.exceptions.WidgetNotFoundException;
import org.miro.widget.exceptions.WidgetCreateException;
import org.miro.widget.mapper.WidgetResponseMapper;
import org.miro.widget.repository.InMemoryWidgetRepository;
import org.miro.widget.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.miro.widget.helper.AppUtils.currentDate;
import static org.miro.widget.helper.AppUtils.getUUID;

@Service
public class WidgetServiceImpl implements WidgetService {

    @Autowired
    private WidgetResponseMapper mapper;

    @Autowired
    private InMemoryWidgetRepository repository;

    @Override
    public WidgetCreateResponse save(MiroWidget widget) {
        String uuid = getUUID();
        widget.setUuid(uuid);
        widget.setLastModificationDate(currentDate());
        return of(repository.save(widget))
                .filter(i -> i == Boolean.TRUE)
                .map(i -> WidgetCreateResponse.builder()
                        .uuid(uuid)
                        .message("Widget created successfully!! uuid = " + uuid)
                        .build())
                .orElseThrow(() -> new WidgetCreateException("Widget failed to create!"));
    }

    @Override
    public AllWidgetResponse getAll() {
        return of(repository.getAll())
                .map(list -> AllWidgetResponse
                        .builder()
                        .widgets(mapper.widgetResponseListFromMiroWidgetList(list))
                        .build())
                .orElseThrow(() -> new ReadDataException("Failed while reading data!"));
    }

    @Override
    public AllWidgetResponse getAll(int pageNumber, int pageSize) {
        return of(repository.getAll())
                .map(list -> {
                            Pageable pageable = new Pageable(mapper.widgetResponseListFromMiroWidgetList(list));
                            pageable.setPage(pageNumber);
                            pageable.setPageSize(pageSize);
                            return AllWidgetResponse
                                    .builder()
                                    .widgets(pageable.getContent())
                                    .build();
                        }
                ).orElseThrow(() -> new ReadDataException("Failed while reading data!"));
    }

    @Override
    public WidgetResponse getById(String uuid) {
        return ofNullable(repository.getById(uuid))
                .map(mapper::widgetResponseFromMiroWidget)
                .orElseThrow(() -> new WidgetNotFoundException("Widget not found against uuid = " + uuid));
    }

    @Override
    public WidgetResponse update(String uuid, MiroWidget widget) {
        widget.setUuid(uuid);
        return ofNullable(repository.update(widget))
                .map(mapper::widgetResponseFromMiroWidget)
                .orElseThrow(() -> new WidgetNotFoundException("Widget not found against uuid = " + widget.getUuid()));
    }

    @Override
    public WidgetDeleteResponse delete(String uuid) {
        return of(repository.delete(uuid))
                .filter(i -> i == Boolean.TRUE)
                .map(i -> WidgetDeleteResponse.builder()
                        .deleteWidgets(1)
                        .message(String.format("Widget %s successfully deleted", uuid))
                        .build())
                .orElseThrow(() -> new WidgetNotFoundException("Widget not found against uuid = " + uuid));
    }

    public WidgetDeleteResponse deleteAll() {
        return of(repository.deleteAll())
                .filter(i -> i != 0)
                .map(i -> WidgetDeleteResponse.builder()
                        .deleteWidgets(i)
                        .message(String.format("%d widgets are deleted successfully", i))
                        .build())
                .orElseThrow(() -> new EmptyStackException());
    }
}
