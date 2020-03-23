package site.yazago.spenging.telegram.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import site.yazago.spenging.telegram.configuration.properties.ProcessorsProperties;


@Order()
@Component
@RequiredArgsConstructor
public class DefaultProcessor implements IMessageProcessor {

    private final ProcessorsProperties properties;

    @Override
    public boolean isApply(Message message) {
        return true;
    }

    @Override
    public SendMessage process(Message message) {
        return new SendMessage().setChatId(message.getChatId()).setText(properties.getDefaultProcessorMessage());
    }
}
