package testClasses;

import io.quarkus.logging.Log;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(value = MethodOrderer.Alphanumeric.class)
class MathOperationsTest {

    private static MathOperations mathOperations;

    @BeforeAll
    static void setUp() {
        Log.info("Setting all tests");
        mathOperations = new MathOperations();
    }

    @AfterAll
    static void tearDown() {
        Log.info("**** Ending Tests ****");
    }

    @DisplayName("""
            This test class is responsible for handling math operations
            """)


    @Test
    @Order(2)
    void test_add(){
        //given

        //when
        //then
        assertAll(
                () -> assertEquals(5,mathOperations.add(1,4)),
                () -> assertEquals(10,mathOperations.add(5,5)),
                () -> assertFalse(10 != mathOperations.add(5,5))
        );
    }

    @Test
    @Order(1)
    void divide_per_zero(){
        //when
        int dividend = 1;
        int divisor = 0;

        //when
        //then
        assertThrows(ArithmeticException.class, () -> mathOperations.divide(dividend,divisor));
    }

    /*

    @Test
    void test_add_with_current_result() {
        //given
        MathOperations mathOperations = new MathOperations();

        //when
        var res = mathOperations.add(10,10);
        //then
        assertEquals(20,res);
    }

    @Test
    void test_add_with_wrong_result() {
        MathOperations mathOperations = new MathOperations();
        var res = mathOperations.add(10,10);
        assertFalse(res != 20);
    }

    @Test
    void test_divide_with_current_result() {
        MathOperations mathOperations = new MathOperations();
        var res = mathOperations.divide(10,10);
        assertEquals(1,res);
    }

    @Test
    void test_divide_with_wrong_result() {
        MathOperations mathOperations = new MathOperations();
        var res = mathOperations.divide(10,10);
        assertFalse(res != 1);
    } */
}