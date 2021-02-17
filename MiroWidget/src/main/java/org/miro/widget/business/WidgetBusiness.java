package org.miro.widget.business;

import org.miro.widget.domain.Pageable;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.response.AllWidgetResponse;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.dto.response.WidgetDeleteResponse;
import org.miro.widget.dto.response.WidgetResponse;
import org.miro.widget.exceptions.RequestException;
import org.miro.widget.mapper.MiroWidgetMapper;
import org.miro.widget.service.impl.WidgetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static java.util.Optional.ofNullable;

@Component
public class WidgetBusiness {

    @Autowired
    private MiroWidgetMapper mapper;

    @Autowired
    private WidgetServiceImpl service;

    public WidgetCreateResponse create(WidgetRequest request) {
        return ofNullable(request).map(rq -> ofNullable(rq.getCoordinateRequest())
                .map(c -> {
                    validateWidgetRequest(rq);
                    return service.save(mapper.miroWidgetFromWidgetRequest(request));
                }).orElseThrow(() -> {
                    throw new RequestException("[Request] validation failure! coordinate cannot be null");
                })).orElseThrow(() -> {
            throw new RequestException("[Request] validation failure");
        });
    }

    public AllWidgetResponse getAll() {
        return service.getAll();
    }


    public AllWidgetResponse getAll(int pageNumber, int pageSize) {
        return service.getAll(pageNumber, pageSize);
    }

    public WidgetResponse getById(String uuid) {
        validateUUID(uuid);
        return service.getById(uuid);
    }

    public WidgetResponse update(String uuid, WidgetRequest request) {
        validateUUID(uuid);

        return ofNullable(request).map(req -> ofNullable(req.getCoordinateRequest())
                .map(c -> {
                    validateWidgetRequest(req);
                    return service.update(uuid, mapper.miroWidgetFromWidgetRequest(request));
                })
                .orElseThrow(() -> {
                    throw new RequestException("[Request] validation failure! coordinate cannot be null");
                }))
                .orElseThrow(() -> {
                    throw new RequestException("[Request] validation failure");
                });
    }

    public WidgetDeleteResponse delete(String uuid) {
        validateUUID(uuid);

        return service.delete(uuid);
    }

    public WidgetDeleteResponse deleteAll() {
        return service.deleteAll();
    }

    private void validateUUID(String uuid) {
        if (Objects.isNull(uuid) || uuid.isBlank())
            throw new RequestException("[Request] validation failure! uuid is blank");
    }

    private void validateWidgetRequest(WidgetRequest request) {
        ofNullable(request.getWidth()).ifPresent(width -> {
            if (width <= 0)
                throw new RequestException("[Request] validation failure!! width should be greater than 0");
        });

        ofNullable(request.getHeight()).ifPresent(height -> {
            if (height <= 0)
                throw new RequestException("[Request] validation failure!! height should be greater than 0");
        });
    }
}
