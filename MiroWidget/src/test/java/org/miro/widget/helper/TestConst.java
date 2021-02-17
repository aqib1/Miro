package org.miro.widget.helper;

import org.springframework.http.MediaType;

public interface TestConst {
    String BASE_URL = "/widget";
    String GET_ALL_BY_PAGING_URL = BASE_URL + "/paging" ;
    MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json");
}
