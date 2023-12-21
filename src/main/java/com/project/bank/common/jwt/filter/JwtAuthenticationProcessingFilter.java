package com.project.bank.common.jwt.filter;

import com.project.bank.common.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String headerToken = jwtService.extractToken(request);
        if (headerToken != null && jwtService.isTokenValid(headerToken)) {
            SecurityContextHolder.getContext().setAuthentication(jwtService.getAuthentication(headerToken));
        }
        filterChain.doFilter(request, response);
    }
}
