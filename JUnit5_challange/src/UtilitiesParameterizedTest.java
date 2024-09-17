import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesParameterizedTest {

    Utilities utilities;

    @BeforeEach

    public void setUp() {
        utilities = new Utilities();
    }

    public static Stream<Arguments> everyNthChar_data() {
        return Stream.of(
                Arguments.of("Hello".toCharArray(),2,"el".toCharArray()),
                Arguments.of("meow".toCharArray(),5,"meow".toCharArray()),
                Arguments.of("l".toCharArray(),2,"l".toCharArray()),
                Arguments.of("".toCharArray(),2,"".toCharArray())
        );
    }

    @ParameterizedTest
    @MethodSource("everyNthChar_data")
    public void everyNthChar(char[] input, int n, char[] expected) {
        char[] output = utilities.everyNthChar(input, n);
        assertArrayEquals(expected, output);
    }


    public static Stream<Arguments> removePairs_data() {
        return Stream.of(
                Arguments.of("AABCDDEFF", "ABCDEF"),
                Arguments.of("ABCCABDEEF", "ABCABDEF"),
                Arguments.of("ZYZQQB", "ZYZQB"),
                Arguments.of("AB88EFFG", "AB8EFG"),
                Arguments.of("1122334455", "12345"),
                Arguments.of("A", "A"),
                Arguments.of("", "")
        );
    }


    @ParameterizedTest
    @MethodSource("removePairs_data")
    public void removePairs(String input, String expected) {
        String output = utilities.removePairs(input);
        assertEquals(expected, output);
    }

    @Test
    public void removePairsNull() {
        assertNull(null, utilities.removePairs(null));
    }

    @Test
    public void converter_checkForException() {
         assertThrows(ArithmeticException.class, ()->utilities.converter(10,0));

    }


    @Test
    public void nullIfOddLength_notNull() {
        assertNotNull("even", utilities.nullIfOddLength("even"));
    }
    @Test
    public void nullIfOddLength_Null() {
        assertNull(null, utilities.nullIfOddLength("odd"));

    }
}