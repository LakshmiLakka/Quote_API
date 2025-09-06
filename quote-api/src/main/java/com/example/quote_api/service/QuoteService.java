package com.example.quote_api.service;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteService {

    private final List<String> quotes = List.of(
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",
            "Believe you can and you're halfway there. - Theodore Roosevelt",
            "Don’t watch the clock; do what it does. Keep going. - Sam Levenson",
            "Act as if what you do makes a difference. It does. - William James"
    );

    private final Random rand = new Random();

    public String getRandomQuote() {
        return quotes.get(rand.nextInt(quotes.size()));
    }
}
