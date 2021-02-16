package org.miro.widget.business;

import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.exceptions.RequestException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class WidgetBusiness {

    public WidgetCreateResponse create(WidgetRequest request) {
        ofNullable(request).ifPresentOrElse(rq -> {
            validateWidgetRequest(rq);
        }, () -> {throw new RequestException("[Request] validation failure");});

        return null;
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
