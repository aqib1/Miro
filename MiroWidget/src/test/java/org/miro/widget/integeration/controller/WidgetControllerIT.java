package org.miro.widget.integeration.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.miro.widget.WidgetApplication;
import org.miro.widget.dto.request.WidgetRequest;
import org.miro.widget.dto.response.WidgetCreateResponse;
import org.miro.widget.helper.DataHelper;
import org.miro.widget.repository.InMemoryWidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.miro.widget.helper.DataHelper.asJsonString;
import static org.miro.widget.helper.DataHelper.asObjectFromJsonString;
import static org.miro.widget.helper.TestConst.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = WidgetApplication.class)
public class WidgetControllerIT {

    private final DataHelper helper = DataHelper.getInstance();
    @Autowired
    private MockMvc mvc;
    @Autowired
    private InMemoryWidgetRepository repository;

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testCreate() throws Exception {
        mvc.perform(post(BASE_URL)
                .content(asJsonString(helper.widgetRequest()))
                .contentType(MEDIA_TYPE_JSON_UTF8))
                .andDo(print())
                .andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        String result = mvc.perform(post(BASE_URL)
                .content(asJsonString(helper.widgetRequest()))
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        WidgetCreateResponse widget = asObjectFromJsonString(result, WidgetCreateResponse.class);
        mvc.perform(put(BASE_URL + "/" + widget.getUuid())
                .content(asJsonString(helper.widgetRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        String result = mvc.perform(post(BASE_URL)
                .content(asJsonString(helper.widgetRequest()))
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        WidgetCreateResponse widget = asObjectFromJsonString(result, WidgetCreateResponse.class);
        mvc.perform(delete(BASE_URL + "/" + widget.getUuid())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deleteWidgets").value(1))
                .andExpect(jsonPath("$.message").value(String.format("Widget %s successfully deleted", widget.getUuid())));
    }

    @Test
    public void testGetById() throws Exception {
        WidgetRequest request = helper.widgetRequest();
        String result = mvc.perform(post(BASE_URL)
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        WidgetCreateResponse widget = asObjectFromJsonString(result, WidgetCreateResponse.class);
        mvc.perform(get(BASE_URL + "/" + widget.getUuid())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.uuid").value(widget.getUuid()))
                .andExpect(jsonPath("$.width").value(request.getWidth()))
                .andExpect(jsonPath("$.height").value(request.getHeight()))
                .andExpect(jsonPath("$.coordinateResponse.x").value(request.getCoordinateRequest().getX()))
                .andExpect(jsonPath("$.coordinateResponse.y").value(request.getCoordinateRequest().getY()))
                .andExpect(jsonPath("$.coordinateResponse.z").value(request.getCoordinateRequest().getZ()));
    }

    @Test
    public void testGetAll() throws Exception {
        WidgetRequest request = helper.widgetRequest();
         mvc.perform(post(BASE_URL)
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

         mvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                 .andDo(print())
                 .andExpect(jsonPath("$.widgets[0].width").value(request.getWidth()))
                 .andExpect(jsonPath("$.widgets[0].height").value(request.getHeight()))
                 .andExpect(jsonPath("$.widgets[0].coordinateResponse.x").value(request.getCoordinateRequest().getX()))
                 .andExpect(jsonPath("$.widgets[0].coordinateResponse.y").value(request.getCoordinateRequest().getY()))
                 .andExpect(jsonPath("$.widgets[0].coordinateResponse.z").value(request.getCoordinateRequest().getZ()));
    }

    @Test
    public void testCreateInvalidWidth() throws Exception {
        mvc.perform(post(BASE_URL)
                .content(asJsonString(helper.invalidWidthWidgetRequest()))
                .contentType(MEDIA_TYPE_JSON_UTF8))
                .andDo(print())
                .andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.detailedMessage").value("[Request] validation failure!! width should be greater than 0 [Internal server exception! => (RequestException)]"))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.exceptionName").value("org.miro.widget.exceptions.RequestException"));
    }

    @Test
    public void testCreateInvalidHeight() throws Exception {
        mvc.perform(post(BASE_URL)
                .content(asJsonString(helper.invalidHeightWidgetRequest()))
                .contentType(MEDIA_TYPE_JSON_UTF8))
                .andDo(print())
                .andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.detailedMessage").value("[Request] validation failure!! height should be greater than 0 [Internal server exception! => (RequestException)]"))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.exceptionName").value("org.miro.widget.exceptions.RequestException"));
    }

    @Test
    public void testGetByUnknownId() throws Exception {
        mvc.perform(get(BASE_URL + "/unknown")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.detailedMessage").value("Widget not found against uuid = unknown [Internal server exception! => (WidgetNotFoundException)]"))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.exceptionName").value("org.miro.widget.exceptions.WidgetNotFoundException"))
                .andExpect(jsonPath("$.errorMessage").value("Widget not found against uuid = unknown"));
    }

    @Test
    public void testUpdateByUnknownId() throws Exception {
        mvc.perform(put(BASE_URL + "/unknown")
                .content(asJsonString(helper.widgetRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.detailedMessage").value("Widget not found against uuid = unknown [Internal server exception! => (WidgetNotFoundException)]"))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.exceptionName").value("org.miro.widget.exceptions.WidgetNotFoundException"))
                .andExpect(jsonPath("$.errorMessage").value("Widget not found against uuid = unknown"));
    }

    @Test
    public void testDeleteByUnknownId() throws Exception {
        mvc.perform(delete(BASE_URL + "/unknown")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.detailedMessage").value("Widget not found against uuid = unknown [Internal server exception! => (WidgetNotFoundException)]"))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.exceptionName").value("org.miro.widget.exceptions.WidgetNotFoundException"))
                .andExpect(jsonPath("$.errorMessage").value("Widget not found against uuid = unknown"));
    }

    @Test
    public void testDeleteAllWhenDataIsUnAvailable() throws Exception {
        mvc.perform(delete(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.detailedMessage").value("java.util.EmptyStackException [Internal server exception! => (EmptyStackException)]"))
                .andExpect(jsonPath("$.errorCode").value(500))
                .andExpect(jsonPath("$.exceptionName").value("java.util.EmptyStackException"));
    }

    @Test
    public void testShouldReturnListWithPaging() throws Exception {
        WidgetRequest request = helper.widgetRequest();
        mvc.perform(post(BASE_URL)
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        mvc.perform(get(GET_ALL_BY_PAGING_URL+"?page=1&pageSize=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
