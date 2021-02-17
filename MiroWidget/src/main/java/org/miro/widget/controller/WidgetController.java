package org.miro.widget.controller;

import io.swagger.annotations.ApiOperation;
import org.miro.widget.business.WidgetBusiness;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.response.AllWidgetResponse;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.dto.response.WidgetDeleteResponse;
import org.miro.widget.dto.response.WidgetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.miro.widget.helper.Const.*;

@RestController
@RequestMapping(BASE_URL)
public class WidgetController {

    @Autowired
    private WidgetBusiness widgetBusiness;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new miro widget", response = WidgetCreateResponse.class)
    public ResponseEntity<WidgetCreateResponse> create(@Valid @RequestBody WidgetRequest request) {
        return new ResponseEntity<>(widgetBusiness.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all widgets", response = AllWidgetResponse.class)
    public ResponseEntity<AllWidgetResponse> getAll() {
        return ResponseEntity.ok(widgetBusiness.getAll());
    }

    @GetMapping(GET_BY_UUID_WIDGET_URL)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get widget by given id", response = WidgetResponse.class)
    public ResponseEntity<WidgetResponse> getById(@PathVariable("id") String uuid) {
        return ResponseEntity.ok(widgetBusiness.getById(uuid));
    }

    @PutMapping(UPDATE_BY_UUID_WIDGET_URL)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update Widget", response = WidgetResponse.class)
    public ResponseEntity<WidgetResponse> update(@PathVariable("id") String uuid, @Valid @RequestBody WidgetRequest request) {
        return ResponseEntity.ok(widgetBusiness.update(uuid, request));
    }

    @DeleteMapping(DELETE_BY_UUID_WIDGET_URL)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Widget", response = WidgetDeleteResponse.class)
    public ResponseEntity<WidgetDeleteResponse> delete(@PathVariable("id") String uuid) {
        return ResponseEntity.ok(widgetBusiness.delete(uuid));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Widget", response = WidgetDeleteResponse.class)
    public ResponseEntity<WidgetDeleteResponse> deleteAll() {
        return ResponseEntity.ok(widgetBusiness.deleteAll());
    }


    @GetMapping(GET_ALL_BY_PAGING_URL)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "List Widgets With Paging", response = AllWidgetResponse.class)
    public ResponseEntity<AllWidgetResponse> getAll(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(widgetBusiness.getAll(page, pageSize));
    }
}
