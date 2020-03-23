package site.yazago.spenging.telegram.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.api.objects.Update;
import site.yazago.spenging.telegram.configuration.properties.SpendingBotProperties;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@Slf4j
@Component
public class SpendingBot extends TelegramLongPollingBot {

    private final SpendingBotProperties botProperties;

    public SpendingBot(SpendingBotProperties botProperties) {
        super(getBotOptions(botProperties));
        this.botProperties = botProperties;
    }

    private static DefaultBotOptions getBotOptions(SpendingBotProperties botProperties) {

        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(botProperties.getProxyUser(), botProperties.getProxyPassword().toCharArray());
            }
        });
        options.setProxyHost(botProperties.getProxyHost());
        options.setProxyPort(botProperties.getProxyPort());
        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

        return options;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("messageReceived: " + update.getMessage());
    }

    @Override
    public String getBotUsername() {
        return botProperties.getName();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }


}
