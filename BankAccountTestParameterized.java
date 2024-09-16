import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTestParameterized {

    private BankAccount account;


    @BeforeEach
    public void setUp() {
        account = new BankAccount("Tim", "Thom", 1000.00, BankAccount.CHECKING);
        System.out.println("Running test....");
    }


    //Arguments - provides test parameters and it's used in collection API - possible also with stream
    private static Stream<Arguments> testParameters() {
        return Stream.of(
                Arguments.of(100.00, true, 1100.00),
                Arguments.of(200.00, true, 1200.00),
                Arguments.of(325.14, true, 1325.14),
                Arguments.of(489.33, true, 1489.33),
                Arguments.of(1000.00, true, 2000.00)
        );
    }

    @ParameterizedTest
    @MethodSource("testParameters")
    public void deposit(double amount, boolean branch, double expected){
        double balance = account.deposit(amount, true);
        assertEquals(expected, balance, .01);
    }
}