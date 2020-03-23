package site.yazago.spenging.telegram.processor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


public interface IMessageProcessor {

    boolean isApply(Message message);

    SendMessage process(Message message);
}
