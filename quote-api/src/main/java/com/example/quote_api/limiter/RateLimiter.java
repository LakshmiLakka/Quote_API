package com.example.quote_api.limiter;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiter {

    private final int MAX_REQUESTS = 5;  
    private final long TIME_WINDOW = 60 * 1000;

    private final Map<String, List<Long>> requestLogs = new ConcurrentHashMap<>();

    public synchronized boolean allowRequest(String clientIp) {
        long currentTime = System.currentTimeMillis();
        requestLogs.putIfAbsent(clientIp, new ArrayList<>());

        List<Long> timestamps = requestLogs.get(clientIp);
        timestamps.removeIf(time -> (currentTime - time) > TIME_WINDOW);

        if (timestamps.size() >= MAX_REQUESTS) {
            return false;
        }

        timestamps.add(currentTime);
        return true;
    }

    public long getRetryAfter(String clientIp) {
        List<Long> timestamps = requestLogs.get(clientIp);
        if (timestamps == null || timestamps.isEmpty()) return 0;
        long oldest = timestamps.get(0);
        long currentTime = System.currentTimeMillis();
        return Math.max(0, (TIME_WINDOW - (currentTime - oldest)) / 1000);
    }
}
