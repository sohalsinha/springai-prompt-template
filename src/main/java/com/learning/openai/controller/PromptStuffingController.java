package com.learning.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class PromptStuffingController {

    private ChatClient chatClient;

    private PromptStuffingController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/templates/systemPromptTemplate.st")
    Resource systemPromptTemplate;

    //Only to be used if the prompt stuffing is small
    @GetMapping("/promt-stuffing")
    public String promtStuffing(@RequestParam("message") String message) {

        return chatClient
                .prompt()
                .system(systemPromptTemplate)
                .user(message)
                .call()
                .content();
    }


}
