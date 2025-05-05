package com.javatechie.dto;

import lombok.Data;

@Data
public class GetAccessTokenResponse {
    private boolean error;
    private int code;
    private String message;
    private TokenData data;

    @Data
    public static class TokenData {
        private String access_token;
        private long expires_in;
    }

	
}
