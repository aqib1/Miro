package org.miro.widget.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseError {
    private String createdAt;
    private String detailedMessage;
    private int errorCode;
    private String exceptionName;
    private String errorMessage;
}
