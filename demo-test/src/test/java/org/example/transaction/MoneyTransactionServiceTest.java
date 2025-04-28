package org.example.transaction;

import org.example.exception.NotEnoughMoneyException;
import org.example.model.Account;
import org.example.service.MoneyServiceTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("Money Transaction Service test")
public class MoneyTransactionServiceTest {

    private static final String MONEY_AMOUNT_EXCEPTION_MSG = "Money amount should be greater than 0";
    private static final String ACCOUNT_EXCEPTION_MSG = "Accounts shouldn't be null";
    private static final double RANDOM_MONEY_AMOUNT = 100;
    private static final double ZERO_MONEY_AMOUNT = 0;
    private static final double MORE_THAN_RANDOM_MONEY_AMOUNT = 200;
    private static final double NEGATIVE_MONEY_AMOUNT = -1;

    private MoneyServiceTransaction testInstance;


    @BeforeEach
    void setUp() {
        testInstance = new MoneyServiceTransaction();
    }



    @Test
    @DisplayName("Vérifier la transaction d'argent d'un compte à un autre")
    void shouldTransferMoneyFromOneAccountToAnother() {
        // GIVEN
        var account1 = new Account(RANDOM_MONEY_AMOUNT);
        var account2 = new Account(ZERO_MONEY_AMOUNT);

        // WHEN
        testInstance.transferMoney(account1, account2, RANDOM_MONEY_AMOUNT);

        // THEN
        assertEquals(ZERO_MONEY_AMOUNT, account1.getMoneyAmount());
        assertEquals(RANDOM_MONEY_AMOUNT, account2.getMoneyAmount());
    }

    @Test
    @DisplayName("Lancer une exception si le compte source est null")
    void shouldThrowExceptionIfAccountFromIsNull() {
        // GIVEN
        Account account1 = null;
        Account account2 = new Account(RANDOM_MONEY_AMOUNT);

        // WHEN & THEN
        var exception = assertThrows(IllegalArgumentException.class,
                () -> testInstance.transferMoney(account1, account2,
                        RANDOM_MONEY_AMOUNT));

        assertEquals(ACCOUNT_EXCEPTION_MSG, exception.getMessage());
    }


    @Test
    @DisplayName("Lancer une exception si le compte destinataire est null")
    void shouldThrowExceptionIfAccountToIsNull() {
        // GIVEN
        Account account1 = new Account(RANDOM_MONEY_AMOUNT);
        Account account2 = null;

        // WHEN & THEN
        var exception = assertThrows(IllegalArgumentException.class,
                () -> testInstance.transferMoney(account1, account2,
                        RANDOM_MONEY_AMOUNT));

        assertEquals(ACCOUNT_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    @DisplayName("Lancer une exception de manque d'argent lors du transfert d'un montant supérieur")
    void shouldThrowNotEnoughMoneyExceptionWhenTransferMoreMoney() {
        // GIVEN
        var account1 = new Account(RANDOM_MONEY_AMOUNT);
        var account2 = new Account(ZERO_MONEY_AMOUNT);

        // WHEN
        assertThrows(NotEnoughMoneyException.class,
                () -> testInstance.transferMoney(account1, account2,
                        MORE_THAN_RANDOM_MONEY_AMOUNT));

    }

    @Test
    @DisplayName("Lancer une exception lors du transfert d'un montant négatif")
    void shouldThrowExcpetionWhenTransferNegativeAmount() {
        // GIVEN
        var account1 = new Account();
        var account2 = new Account();

        // WHEN
        var exception = assertThrows(IllegalArgumentException.class,
                () -> testInstance.transferMoney(account1, account2,
                        NEGATIVE_MONEY_AMOUNT));

        assertEquals(MONEY_AMOUNT_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    @DisplayName("Lancer une exception lors du transfert d'un montant nul")
    void shouldThrowExcpetionWhenTransferZeroMoneyAmount() {
        // GIVEN
        var account1 = new Account();
        var account2 = new Account();

        // WHEN
        var exception = assertThrows(IllegalArgumentException.class,
                () -> testInstance.transferMoney(account1, account2,
                        ZERO_MONEY_AMOUNT));

        assertEquals(MONEY_AMOUNT_EXCEPTION_MSG, exception.getMessage());
    }


    @Test
    @DisplayName("Exemples d'assertions dépendantes")
    void dependentAssertionsExample() {

        // GIVEN
        var account1 = new Account(RANDOM_MONEY_AMOUNT);
        var account2 = new Account(ZERO_MONEY_AMOUNT);

        assertAll("Money Transaction",
                () -> {
                    // WHEN
                    boolean isTransactionSuccesd = testInstance.transferMoney(account1, account2, RANDOM_MONEY_AMOUNT);
                    assertTrue(isTransactionSuccesd);


                    assertAll("Money amount is changed on the accounts",
                            () -> assertEquals(ZERO_MONEY_AMOUNT, account1.getMoneyAmount()),
                            () -> assertEquals(RANDOM_MONEY_AMOUNT, account2.getMoneyAmount())
                    );
                }
        );
    }

    @Test
    @DisplayName("Test avec délai d'expiration")
    void testWithTimeoutExample() {
        // GIVEN
        var account1 = new Account(RANDOM_MONEY_AMOUNT);
        var account2 = new Account(ZERO_MONEY_AMOUNT);

        assertTimeout(Duration
                .ofSeconds(1), () ->
                testInstance
                        .transferMoney(account1, account2, RANDOM_MONEY_AMOUNT));
    }

    @Test
    @Timeout(2)
    @DisplayName("Le temps n'est pas dépassé avec résultat")
    void timeoutNotExceededWithResult() {
        // GIVEN
        var account1 = new Account(RANDOM_MONEY_AMOUNT);
        var account2 = new Account(ZERO_MONEY_AMOUNT);

        boolean actualResult = assertTimeout(Duration.ofSeconds(1), () -> {
            return testInstance
                    .transferMoney(account1, account2, RANDOM_MONEY_AMOUNT);
        });
        assertTrue(actualResult);
    }


    @ParameterizedTest
    @ValueSource(ints = {100, 200, 50, -10})
    @DisplayName("Exemple de test paramétrisé")
    void parametrizedTestExample(int moneyAmount) {
        assumeTrue(moneyAmount > 0, () -> "Money amount can't be negative");

        var account1 = new Account(moneyAmount);
        var account2 = new Account(ZERO_MONEY_AMOUNT);

        assertTrue(testInstance.transferMoney(account1, account2, moneyAmount));

    }



}
