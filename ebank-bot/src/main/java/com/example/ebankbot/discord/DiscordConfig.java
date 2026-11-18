package com.example.ebankbot.discord;

import com.example.ebankbot.agents.EbankAiAgent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordConfig {

    @Bean
    public JDA jda(@Value("${discord.token}") String token,
                   EbankAiAgent agent) throws InterruptedException {
        return JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new ListenerAdapter() {
                    @Override
                    public void onMessageReceived(MessageReceivedEvent event) {
                        if (event.getAuthor().isBot()) return;
                        try {
                            String answer = agent.chat(
                                    event.getMessage().getContentRaw(),
                                    event.getAuthor().getId());
                            if (answer.length() > 2000) answer = answer.substring(0, 2000);
                            event.getChannel().sendMessage(answer).queue();
                        } catch (Exception e) {
                            e.printStackTrace();
                            event.getChannel().sendMessage("Error: " + e.getMessage()).queue();
                        }
                    }
                })
                .build()
                .awaitReady();
    }
}