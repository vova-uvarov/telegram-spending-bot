package site.yazago.spenging.telegram.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import site.yazago.spenging.telegram.configuration.properties.ProcessorsProperties;

@Order(2)
@Component
@RequiredArgsConstructor
public class HelpProcessor implements IMessageProcessor {

    private final ProcessorsProperties properties;

    @Override
    public boolean isApply(Message message) {
        String text = message.getText();
        return text.startsWith("/help") || text.trim().equalsIgnoreCase("Помощь"); // todo
    }

    @Override
    public SendMessage process(Message message) {
        return new SendMessage(message.getChatId(), properties.getHelpProcessorMessage());
    }
}
