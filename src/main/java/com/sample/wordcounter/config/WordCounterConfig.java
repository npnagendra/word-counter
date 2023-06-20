package com.sample.wordcounter.config;

import com.sample.wordcounter.service.Translator;
import com.sample.wordcounter.service.WordCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordCounterConfig {

    @Bean
    public WordCounter wordCounter(Translator translator) {
        return new WordCounter(translator);
    }

    @Bean
    public Translator translator(){
        return new Translator();
    }
}
