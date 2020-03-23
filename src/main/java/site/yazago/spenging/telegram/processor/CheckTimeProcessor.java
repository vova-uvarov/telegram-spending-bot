package site.yazago.spenging.telegram.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import site.yazago.spenging.telegram.configuration.properties.ProcessorsProperties;

@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class CheckTimeProcessor implements IMessageProcessor {

    private final ProcessorsProperties properties;

    @Override
    public boolean isApply(Message message) {
        long diff = System.currentTimeMillis() - (message.getDate() * 1000);

        long diffMinutes = diff / (60 * 1000) % 60;
        return diffMinutes > properties.getMaxMessageTime();
    }

    @Override
    public SendMessage process(Message message) {
        log.info("ignore message: " + message.toString());
        return null;
    }
}
