package se.iths.ellinor.bankaccountapp.service;

import se.iths.ellinor.bankaccountapp.component.AccountComponent;
import se.iths.ellinor.bankaccountapp.exception.InsufficientFundsException;
import se.iths.ellinor.bankaccountapp.exception.InvalidAmountException;
import se.iths.ellinor.bankaccountapp.exception.MaxWithdrawalExceededException;

public class ATMService {
    private AccountComponent account;
    private int maxWithdrawal = 500;

    public ATMService(AccountComponent account) {
        this.account = account;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Error: Deposit must exceed 0 kr");
        }
        account.deposit(amount);
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Error: Withdrawal must exceed 0 kr");
        }
        if (amount > maxWithdrawal) {
            throw new MaxWithdrawalExceededException("Error: Maximum withdrawal of 500 kr exceeded");
        }
        if (amount > account.getBalance()) {
            throw new InsufficientFundsException("Error: Insufficient funds on account");
        }
        account.withdraw(amount);
    }

    public int showFunds() {
        return account.getBalance();
    }
}
