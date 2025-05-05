package com.javatechie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javatechie.dto.GetAccessTokenRequest;
import com.javatechie.dto.GetAccessTokenResponse;
import com.javatechie.service.JwtUtil;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/login")
@Slf4j
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    
    private static final String VALID_CLIENT_SECRET = "8a7f82a1-cb55-4c83-9a68-9ec23a541f21";
    private static final long EXPIRY_SECONDS = 3600;
    
    public AuthController() {
        logger.info("AuthController initialized");
    }

    @PostMapping("/get-access-token")
    public ResponseEntity<GetAccessTokenResponse> getAccessToken(@RequestBody GetAccessTokenRequest request) {
        GetAccessTokenResponse response = new GetAccessTokenResponse();

        try {
            // Validate presence of grant_type
            if (request.getGrantType() == null || request.getGrantType().isEmpty()) {
                response.setError(true);
                response.setCode(2000);
                response.setMessage("Bad Request: grant_type is required.");
                return ResponseEntity.badRequest().body(response);
            }

            // Validate grant_type value
            if (!"client_credentials".equals(request.getGrantType())) {
                response.setError(true);
                response.setCode(2000);
                response.setMessage("Bad Request: Invalid grant_type.");
                return ResponseEntity.badRequest().body(response);
            }

            // Validate presence of client_secret
            if (request.getClientSecret() == null || request.getClientSecret().isEmpty()) {
                response.setError(true);
                response.setCode(2000);
                response.setMessage("Bad Request: client_secret is required.");
                return ResponseEntity.badRequest().body(response);
            }

            // Validate correctness of client_secret
            if (!VALID_CLIENT_SECRET.equals(request.getClientSecret())) {
                response.setError(true);
                response.setCode(2001);
                response.setMessage("Unauthorized: Invalid client_secret.");
                return ResponseEntity.status(401).body(response);
            }

            // Generate token
            String token = JwtUtil.generateToken("client", EXPIRY_SECONDS);

            // Build response
            GetAccessTokenResponse.TokenData data = new GetAccessTokenResponse.TokenData();
            data.setAccess_token(token);
            data.setExpires_in(EXPIRY_SECONDS);

            response.setError(false);
            response.setCode(1000);
            response.setMessage("Access token generated successfully.");
            response.setData(data);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Internal server error while generating token", e);
            response.setError(true);
            response.setCode(5000);
            response.setMessage("Internal Server Error: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
