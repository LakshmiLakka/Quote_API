package com.example.quote_api.limiter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiter rateLimiter;
    private static final int SC_TOO_MANY_REQUESTS = 429;

    public RateLimitInterceptor(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {
        String clientIp = request.getRemoteAddr();

        if (!rateLimiter.allowRequest(clientIp)) {
            long retryAfter = rateLimiter.getRetryAfter(clientIp);
            response.setStatus(SC_TOO_MANY_REQUESTS);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"error\": \"Rate limit exceeded. Try again in " + retryAfter + " seconds.\"}"
            );
            return false;
        }
        return true;
    }
}
