package org.example.service;

import org.example.exception.NotEnoughMoneyException;
import org.example.model.Account;

public class MoneyServiceTransaction {

    public boolean transferMoney(Account accountFrom, Account accountTo, double moneyAmountToSend) {
        if (accountFrom == null || accountTo == null ) {
            throw new IllegalArgumentException("Accounts shouldn't be null");
        }

        if (moneyAmountToSend <= 0) {
            throw new IllegalArgumentException("Money amount should be greater than 0");
        }

        if (accountFrom.getMoneyAmount() < moneyAmountToSend) {
            throw new NotEnoughMoneyException();
        }

        accountFrom.setMoneyAmount(accountFrom.getMoneyAmount() - moneyAmountToSend);
        accountTo.setMoneyAmount(accountTo.getMoneyAmount() + moneyAmountToSend);

        return true;
    }



}