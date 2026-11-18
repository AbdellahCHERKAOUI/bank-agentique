package com.example.ebankbot.telegram;
import com.example.ebankbot.agents.EbankAiAgent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class EbankTelegramBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;
    private final EbankAiAgent agent;
    private final String token;

    public EbankTelegramBot(@Value("${telegram.bot.token}") String token,
                            EbankAiAgent agent) {
        this.token = token;
        this.agent = agent;
        this.telegramClient = new OkHttpTelegramClient(token);
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        String answer;
        try {
            answer = agent.chat(text, String.valueOf(chatId));  // conversationId = chat
        } catch (Exception e) {
            e.printStackTrace();
            answer = "Error: " + e.getMessage();
        }

        // Telegram limite les messages à 4096 caractères
        if (answer.length() > 4096) answer = answer.substring(0, 4096);

        try {
            telegramClient.execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(answer)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}