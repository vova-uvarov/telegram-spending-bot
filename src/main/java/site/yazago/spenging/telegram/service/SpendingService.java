package site.yazago.spenging.telegram.service;

import org.springframework.stereotype.Service;
import site.yazago.spenging.telegram.model.AccountBalance;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpendingService implements ISpendingService {

    @Override
    public List<AccountBalance> balances() {
        return new ArrayList<>();
    }

}
