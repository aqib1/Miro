package org.miro.widget.business;

import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.exceptions.RequestException;
import org.miro.widget.mapper.MiroWidgetMapper;
import org.miro.widget.service.impl.WidgetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class WidgetBusiness {

    @Autowired
    private MiroWidgetMapper mapper;

    @Autowired
    private WidgetServiceImpl service;

    public WidgetCreateResponse create(WidgetRequest request) {
        return ofNullable(request).map(rq -> {
            validateWidgetRequest(rq);
           return service.save(mapper.miroWidgetFromWidgetRequest(request));
        }).orElseThrow(() -> {throw new RequestException("[Request] validation failure");});
    }

    private void validateWidgetRequest(WidgetRequest request) {
        ofNullable(request.getWidth()).ifPresent(width -> {
            if(width <= 0)
                throw new RequestException("[Request] validation failure!! width should be greater than 0");
        });

        ofNullable(request.getHeight()).ifPresent(height -> {
            if(height <= 0)
                throw new RequestException("[Request] validation failure!! height should be greater than 0");
        });
    }
}
