package com.lighthouse.resultautomation.model.request;

import com.lighthouse.resultautomation.common.ApplicationConstants;
import com.lighthouse.resultautomation.common.ErrorCode;
import com.lighthouse.resultautomation.common.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SignUpRequest {
//    @NotEmpty(message = ErrorCode.INVALID_TOKEN)
//    private String token;
    private String name;
    @Email(regexp = ApplicationConstants.VALID_EMAIL_ADDRESS_REGEX,message = ErrorCode.INVALID_EMAIL)
    private String email;
    @ValidPassword(message = ErrorCode.INVALID_PASSWORD)
    private String password;
    @NotNull(message = ErrorCode.USER_NAME_CANNOT_BE_EMPTY)
    private String userName;
}
