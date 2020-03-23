package site.yazago.spenging.telegram.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import site.yazago.spenging.telegram.configuration.properties.SpendingBotProperties;
import site.yazago.spenging.telegram.processor.ProcessorManager;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@Slf4j
@Component
public class SpendingBot extends TelegramLongPollingBot {

    private final SpendingBotProperties botProperties;
    private final ProcessorManager processorManager;

    public SpendingBot(SpendingBotProperties botProperties, ProcessorManager processorManager) {
        super(getBotOptions(botProperties));
        this.botProperties = botProperties;
        this.processorManager = processorManager;
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
        if (update.hasMessage()) {
            System.out.println("messageReceived: " + update.getMessage());
            SendMessage sendMessage = processorManager.getProcessor(update.getMessage()).process(update.getMessage());
            send(sendMessage);
        }
    }

    private void send(SendMessage sendMessage) {
        try {
            if (sendMessage != null) {
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            log.error("error while send message: ", e);
        }
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
