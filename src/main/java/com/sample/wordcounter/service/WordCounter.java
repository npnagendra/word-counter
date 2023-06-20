package com.sample.wordcounter.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WordCounter {
    private final ConcurrentHashMap<String, AtomicInteger> wordCounts;
    private Translator translator;

    public WordCounter(Translator translator) {
        wordCounts = new ConcurrentHashMap<>();
        this.translator = translator;
    }

    public void addWords(String... words) {
        for (String word : words) {
            if (isValidWord(word)) {
                String translatedWord = translator.translate(word);
                if (translatedWord != null) {
                    String normalizedWord = translatedWord.toLowerCase();
                    wordCounts.computeIfAbsent(normalizedWord, k -> new AtomicInteger()).incrementAndGet();
                }
            }
        }
    }

    public int countWord(String word) {
        AtomicInteger count = new AtomicInteger();
        String normalizedWord = translator.translate(word);
        if (normalizedWord != null) {
            normalizedWord = normalizedWord.toLowerCase();
            count = wordCounts.get(normalizedWord);
        }
        return (count != null) ? count.get() : 0;
    }

    private boolean isValidWord(String word) {
        return word.matches("[a-zA-Z]+");
    }
}