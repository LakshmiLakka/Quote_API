package com.example.quote_api.controller;


import com.example.quote_api.service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/quote")
    public ResponseEntity<Map<String, String>> getQuote() {
        String quote = quoteService.getRandomQuote();
        Map<String, String> response = new HashMap<>();
        response.put("quote", quote);
        return ResponseEntity.ok(response);
    }
}
