package se.iths.ellinor.bankaccountapp.component;

import org.springframework.stereotype.Component;

@Component
public class AccountComponent {

    //klassattribut för saldo
    private int balance = 0;

    //metod för insättning
    public void deposit(int amount) {
        balance += amount;
    }

    //metod för uttag
    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}
