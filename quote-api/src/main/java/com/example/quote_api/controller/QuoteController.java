package com.example.quote_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/quote")
public class QuoteController {

    private static final List<String> QUOTES = List.of(
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",
            "Don’t watch the clock; do what it does. Keep going. - Sam Levenson",
            "Act as if what you do makes a difference. It does. - William James",
            "Believe you can and you're halfway there. - Theodore Roosevelt"
    );

    private final Random random = new Random();

   
    @GetMapping
    public Map<String, String> getQuote() {
        String quote = QUOTES.get(random.nextInt(QUOTES.size()));
        return Map.of("quote", quote);
    }

    @GetMapping("/test")
    public Map<String, String> testQuote() {
        String quote = QUOTES.get(random.nextInt(QUOTES.size()));
        return Map.of("testQuote", quote);
    }
}

