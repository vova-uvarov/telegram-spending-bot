package site.yazago.spenging.telegram.service;

import site.yazago.spenging.telegram.model.AccountBalance;

import java.util.List;

public interface ISpendingService {
    List<AccountBalance> balances();
}
