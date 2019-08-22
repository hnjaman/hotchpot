package com.lighthouse.resultautomation.model.response;

import com.lighthouse.resultautomation.service.JwtToken;
import lombok.Data;

@Data
public class TokenDTO {
    private JwtToken accessToken;
}
