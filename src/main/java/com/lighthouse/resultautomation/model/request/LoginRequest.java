package com.lighthouse.resultautomation.model.request;

import com.lighthouse.resultautomation.common.ErrorCode;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @NotEmpty(message = "can't be empty")
    private String email;
    @NotEmpty(message = ErrorCode.INVALID_PASSWORD)
    private String password;
}
