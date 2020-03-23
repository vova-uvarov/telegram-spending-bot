package site.yazago.spenging.telegram.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProcessorManager {
    private final List<IMessageProcessor> processors;

    public IMessageProcessor getProcessor(Message message) {
        for (IMessageProcessor processor : processors) {
            if (processor.isApply(message)) {
                return processor;
            }
        }
        throw new IllegalArgumentException("Не удалось найти процессор для обработки сообщения"); // todo
    }
}
