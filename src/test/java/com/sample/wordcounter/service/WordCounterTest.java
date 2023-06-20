package com.sample.wordcounter.service;
import com.sample.wordcounter.service.Translator;
import com.sample.wordcounter.service.WordCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterTest {
    private WordCounter wordCounter;

    @BeforeEach
    public void setUp() {
        Translator translator = new Translator();
        wordCounter = new WordCounter(translator);
    }

    @Test
    public void testCountWord() {
        wordCounter.addWords("bmw", "audi", "bmw", "vw", "toyota");
        assertEquals(2, wordCounter.countWord("bmw"));
        assertEquals(1, wordCounter.countWord("audi"));
        assertEquals(0, wordCounter.countWord("honda"));
    }

    @Test
    public void testCountWordWithNonAlphabeticCharacters() {
        wordCounter.addWords("bmw", "12345", "audi", "$123");
        assertEquals(1, wordCounter.countWord("bmw"));
        assertEquals(0, wordCounter.countWord("12345"));
        assertEquals(1, wordCounter.countWord("audi"));
        assertEquals(0, wordCounter.countWord("$123"));
    }
}