package site.yazago.spenging.telegram.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import site.yazago.spenging.telegram.model.AccountBalance;
import site.yazago.spenging.telegram.service.ISpendingService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Order(2)
@Component
@RequiredArgsConstructor
public class BalanceProcessor implements IMessageProcessor {

    private final ISpendingService spendingService;

    @Override
    public boolean isApply(Message message) {
        String text = message.getText();
        return text.startsWith("/balance") || text.trim().equalsIgnoreCase("баланс"); // todo
    }

    @Override
    public SendMessage process(Message message) {
        List<AccountBalance> balances = spendingService.balances();
        String text = balances.stream()
                .filter(b -> !isNil(b))
                .map(this::formatAccount)
                .collect(Collectors.joining("\n"));
        return new SendMessage(message.getChatId(), text);
    }

    private boolean isNil(AccountBalance b) {
        return b.getBalance().compareTo(BigDecimal.ZERO) == 0;
    }

    private String formatAccount(AccountBalance b) {
        return b.getAccountName() + ":" + b.getBalance();
    }
}
