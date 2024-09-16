import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount bankAccount;
    private static int count;

    @BeforeAll//зарежда преди всички тестове само веднъж, а не преди всеки тест метод, както е BeforeEach
    //методът трябва да е public static void
    public static void beforeClass() {
        System.out.println("Execution before any test cases. Count = " + count++);
    }

    @BeforeEach//анотация, която указва че този метод ще се стартира преди всеки един
    public void setup(){
        bankAccount = new BankAccount("Tim","Nicolas",1000,BankAccount.CHECKING);
        System.out.println("Running a test...");

    }

    //evaluate the performance of basic operations
    @Test
    public void depost() {
        double balance = bankAccount.deposit(200,true);
        assertEquals(1200,balance,0);
        //first value is what we expect as answer from balance variable
        // delta - when we compare double.
        //Докато разликата межеу двете стойности е в делта, assert-а би бил верен
    }

    @Test
    public  void widthdraw_branch() {
        bankAccount.widthdraw(600,true);
    }

    @Test
    public  void widthdraw_notBranch() throws Exception {
            bankAccount.widthdraw(600, false);
            fail("Should have thrown an exception");
    }


    //verifying the account's balance after operations
    @Test
    public void getBalance_deposit() {
        bankAccount.deposit(200,true);
        assertEquals(1200,bankAccount.getBalance(),0);
    }
    @Test
    public void getBalance_widthdraw() {
        bankAccount.widthdraw(200,true);
        assertEquals(800,bankAccount.getBalance(),0);
    }
    @Test
    public void isChecking_true(){
        assertTrue(bankAccount.isChecking());
        //optional but not the best approach - assertEquals(true,bankAccount.isChecking());

    }


    @AfterAll//зарежда преди след тестове само веднъж
    //методът трябва да е public static void
    public static void afterClass() {
        System.out.println("Execution after any test cases. Count = " +  count++);
    }

    @AfterEach//ще се изпълнява след всеки тест
    public void teardown(){
        System.out.println("Count = " +  count++);
    }

}