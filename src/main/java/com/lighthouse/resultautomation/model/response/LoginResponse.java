package com.lighthouse.resultautomation.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class LoginResponse {
    private String name;
    private String email;
    private String userName;
}
