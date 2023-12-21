package com.project.bank.common.jwt.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bank.common.dto.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ErrorResponse errorResponse = ErrorResponse.fromStatusAndMessage(
                HttpStatus.UNAUTHORIZED,
                "accessToken이 만료되었습니다. 다시 로그인해주세요."
        );

        Map<String, ErrorResponse> map = Map.of("error", errorResponse);
        String body = objectMapper.writeValueAsString(map);
        response.getWriter().write(body);
    }
}
