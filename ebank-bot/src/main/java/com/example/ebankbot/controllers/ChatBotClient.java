package com.example.ebankbot.controllers;

import com.example.ebankbot.agents.EbankAiAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatBotClient {

    private EbankAiAgent ebankAiAgent;

    public ChatBotClient(EbankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;

    }
    @GetMapping("/chat")
    public String chat(@RequestParam String message,
                       @RequestParam(defaultValue = "default") String conversationId) {
        return ebankAiAgent.chat(message,conversationId);
    }
}
