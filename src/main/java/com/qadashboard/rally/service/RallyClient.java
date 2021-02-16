package com.qadashboard.rally.service;

import com.qadashboard.rally.model.enums.RallyApiEnum;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class RallyClient {

    private final RestTemplate restTemplate;

    public String executeGet(String endpoint) {
        final URI uri = URI.create(RallyApiEnum.BASE.getPath().concat(endpoint));
        return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(createHeaders()), String.class).getBody();
    }

    private HttpHeaders createHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final String username = "exampleuser@testinium.com"; // YOU_CAN_CHANGE
        final String password = "examplePassword"; // YOU_CAN_CHANGE
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        httpHeaders.set("Authorization", authHeader);
        return httpHeaders;
    }
}
