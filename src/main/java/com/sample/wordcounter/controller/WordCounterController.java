package com.sample.wordcounter.controller;

import com.sample.wordcounter.service.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wordcounter")
public class WordCounterController {
    @Autowired
    private WordCounter wordCounter;

    @PostMapping("/addwords")
    public void addWords(@RequestBody String[] words) {
        wordCounter.addWords(words);
    }

    @GetMapping("/count/{word}")
    public int getCount(@PathVariable String word) {
        return wordCounter.countWord(word);
    }
}
