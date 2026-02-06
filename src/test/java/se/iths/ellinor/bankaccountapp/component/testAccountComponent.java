package se.iths.ellinor.bankaccountapp.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testAccountComponent {

    private AccountComponent account;

    @BeforeEach
    public void setup() {
        account = new AccountComponent();
    }

    @Test
    void balanceShouldBeZeroFromStart() {
        assertEquals(0, account.getBalance());
    }

    @Test
    void depositShouldIncreaseBalance() {
        account.deposit(200);
        assertEquals(200, account.getBalance());
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        account.deposit(500);
        account.withdraw(200);

        assertEquals(300, account.getBalance());
    }

    @Test
    void depositAndWithdrawShouldReturnCorrectBalance() {
        account.deposit(700);
        account.withdraw(200);
        account.deposit(100);

        assertEquals(600, account.getBalance());
    }

}
