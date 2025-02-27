package testClasses;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class SayHelloTest {


    public static Stream<Arguments> test_hello_academy_with_valid_name() {
        return Stream.of(
                Arguments.of("Hello Marsul", "Marsul"),
                Arguments.of("Hello ",""),
                Arguments.of("Hello", null)
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_hello_academy_with_valid_name(String expected, String name) {
        // Given
        SayHello sayHello = new SayHello();
        String result =sayHello.sayHello(name);
        //when

        //then
        assertEquals(expected, result);
    }


    @Test
    void test_hello_academy_with_invalid_name() {
        //Given
        SayHello sayHello = new SayHello();
        //when
        String result = sayHello.sayHello(null);
        //then
        assertEquals("Hello", result);
    }
}