package com.example.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class EbankAiAgent {
    private ChatClient chatClient;

    public EbankAiAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("You are an assistant for an e-banking application. " +
                        "Use the available tools to answer questions about customers and accounts." +
                        "si aucun context nést fourni repond ave je ne sais [as")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultToolCallbacks(tools)
                .build();
    }
    public String chat(String message,String conversationId) {
        return chatClient.prompt()
                .user(message)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
