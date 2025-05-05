package com.javatechie.dto;

import lombok.Data;

@Data
public class GetAccessTokenRequest {
    private String grantType;
    private String clientSecret;
}
