package com.sample.wordcounter.service.controller;

import com.sample.wordcounter.controller.WordCounterController;
import com.sample.wordcounter.service.WordCounter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(WordCounterController.class)
public class WordCounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordCounter wordCounter;

    @Test
    public void testAddWords() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wordcounter/addwords")
                        .contentType("application/json")
                        .content("[\"test\", \"test\", \"word\"]"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetCount() throws Exception {
        Mockito.when(wordCounter.countWord("test")).thenReturn(2);

        mockMvc.perform(MockMvcRequestBuilders.get("/wordcounter/count/test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("2"));
    }
}
