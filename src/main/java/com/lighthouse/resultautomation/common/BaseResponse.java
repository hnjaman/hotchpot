package com.lighthouse.resultautomation.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lighthouse.resultautomation.common.enums.ResponseType;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 1546524853123L;

    @JsonProperty("type")
    private ResponseType responseType;

    @JsonProperty("message")
    private Collection<String> message;

    @JsonProperty("result")
    private Object result;

    @JsonProperty("error")
    private Object error;

    @JsonProperty("code")
    private String code;
}
