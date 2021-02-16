package org.miro.widget.controller;

import io.swagger.annotations.ApiOperation;
import org.miro.widget.business.WidgetBusiness;
import org.miro.widget.domain.MiroWidget;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WidgetController {

    @Autowired
    private WidgetBusiness widgetBusiness;

    @PostMapping("/widgets")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new miro widget", response = WidgetCreateResponse.class)
    public ResponseEntity<WidgetCreateResponse> create(@Valid @RequestBody WidgetRequest request) {
        return new ResponseEntity<>(widgetBusiness.create(request), HttpStatus.CREATED);
    }
}
