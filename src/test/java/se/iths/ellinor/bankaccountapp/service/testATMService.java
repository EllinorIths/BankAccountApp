package se.iths.ellinor.bankaccountapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.ellinor.bankaccountapp.component.AccountComponent;
import se.iths.ellinor.bankaccountapp.exception.InsufficientFundsException;
import se.iths.ellinor.bankaccountapp.exception.InvalidAmountException;
import se.iths.ellinor.bankaccountapp.exception.MaxWithdrawalExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class testATMService {

    @Mock
    AccountComponent account;
    //fejkad account

    @InjectMocks
    ATMService service;
    //atmservice injiceras

    @Test
    public void invalidDepositShouldThrowException() {
        int invalidAmount = 0;

        assertThrows(InvalidAmountException.class, () -> {
            service.deposit(invalidAmount);
        });

    }

    @Test
    public void invalidWithdrawalShouldThrowException() {
        int invalidAmount = -3;

        assertThrows(InvalidAmountException.class, () -> {
            service.withdraw(invalidAmount);
        });
    }

    @Test
    public void maxWithdrawalExceededShouldThrowException() {
        int exceededMaxAmount = 501;

        assertThrows(MaxWithdrawalExceededException.class, () -> {
            service.withdraw(exceededMaxAmount);
        });
    }

    @Test
    public void insufficientFundsShouldThrowException() {
        int withdrawal = 100;

        assertThrows(InsufficientFundsException.class, () -> {
            service.withdraw(withdrawal);
        });
    }

    @Test
    public void validAmountShouldCallAccountDeposit() {
        int validAmount = 100;

        service.deposit(validAmount);

        verify(account).deposit(validAmount);
    }

    @Test
    public void validAmountShouldCallAccountWithdraw() {

        //mockar saldot på kontot till 300kr
        when(account.getBalance()).thenReturn(300);

        service.withdraw(100);
        verify(account).withdraw(100);

    }

    @Test
    public void showFundsShouldReturnAccountBalance() {

        //mockar saldot till 1000 kr
        when(account.getBalance()).thenReturn(1000);

        int balance = service.showFunds();

        assertEquals(1000, balance);
    }
}
