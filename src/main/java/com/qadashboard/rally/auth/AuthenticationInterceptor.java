package com.qadashboard.rally.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * YOU_CAN_CHANGE
 * If you dont use any authentication, you can delete this class
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Value("${auth.token}")
    private String accessToken;

    private static final String AUTH_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader == null) throw new AuthenticationException();

        final String token = new String(Base64.getDecoder().decode(authHeader), StandardCharsets.UTF_8);

        if (!token.equals(accessToken)) {
            throw new AuthenticationException();
        }

        return true;
    }
}
