package com.TicketingSystem.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class AuthenticationAuthorizationExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authException) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        handleException(httpServletRequest, httpServletResponse, authException);

    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        handleException(httpServletRequest, httpServletResponse, accessDeniedException);
    }

    private void handleException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception e) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Exception exception = (Exception) httpServletRequest.getAttribute("exception");

        String message;

        if (exception != null){
            byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("cause", exception.toString()));
            httpServletResponse.getOutputStream().write(body);
        }else{
            if (e.getCause() != null){
                message = e.getCause().toString() + " " + e.getMessage();
            }else{
                message = e.getMessage();
            }
            byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
            httpServletResponse.getOutputStream().write(body);
        }
    }
}