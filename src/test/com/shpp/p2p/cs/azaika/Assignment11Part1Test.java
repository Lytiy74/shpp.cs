package test.com.shpp.p2p.cs.azaika;

import com.shpp.p2p.cs.azaika.assignment10.Assignment10Part1;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class Assignment11Part1Test {
    @Test
    public void testInvalidCharacter() {
        String[] args = {"2+a*3"};
        try {
            Assignment10Part1.main(args);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid character: a", e.getMessage());
        }
    }

    @Test
    public void testMultipleInvalidCharacters() {
        String[] args = {"2+@#*3"};
        try {
            Assignment10Part1.main(args);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid character: @", e.getMessage());
        }
    }

    @Test
    public void testInvalidCharacterAtBeginning() {
        String[] args = {"%2+3"};
        try {
            Assignment10Part1.main(args);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid character: %", e.getMessage());
        }
    }

    @Test
    public void testInvalidCharacterAtEnd() {
        String[] args = {"2+3$"};
        try {
            Assignment10Part1.main(args);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid character: $", e.getMessage());
        }
    }
    @Test
    public void testSimpleExpression() {
        String[] args = {"3 + 4 * 2 / ( 1 - 5 ) ^ 2"};
        Queue<String> expectedPostfix = new LinkedList<>(List.of("3", "4", "2", "*", "1", "5", "-", "2", "^", "/", "+"));
        double expectedResult = 3.5;

        test(args, expectedPostfix, expectedResult);
    }

    @Test
    public void testExpressionWithVariables() {
        String[] args = {"a + b * c", "a = 2", "b = 3", "c = 4"};
        Queue<String> expectedPostfix = new LinkedList<>(List.of("v_a", "v_b", "v_c", "*", "+"));
        double expectedResult = 14.0;

        test(args, expectedPostfix, expectedResult);
    }

    @Test
    public void testFunctionExpression() {
        String[] args = {"sin( 3.14159 / 2 )"};
        Queue<String> expectedPostfix = new LinkedList<>(List.of("3.14159", "2", "/", "sin"));
        double expectedResult = 1.0;

        test(args, expectedPostfix, expectedResult);
    }

    @Test
    public void testFunctionWithVariables() {
        String[] args = {"log2( a * b )", "a = 8", "b = 2"};
        Queue<String> expectedPostfix = new LinkedList<>(List.of("v_a", "v_b", "*", "log2"));
        double expectedResult = 4.0;

        test(args, expectedPostfix, expectedResult);
    }


    @Test
    public void testInvalidVariable() {
        String[] args = {"a + b", "a = 2", "b = notANumber"};
        assertThrows(IllegalArgumentException.class, () -> Assignment10Part1.main(args));
    }

    @Test
    public void testVariablesAndFunctions() {
        String[] args = {"sqrt(a^2 + b^2)", "a = 3", "b = 4"};
        Queue<String> expectedPostfix = new LinkedList<>(List.of("v_a", "2", "^", "v_b", "2", "^", "+", "sqrt"));
        double expectedResult = 5.0;

        test(args, expectedPostfix, expectedResult);
    }

    private void test(String[] args, Queue<String> expectedPostfix, double expectedResult) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assignment10Part1.main(args);

        String[] outputLines = outContent.toString().split("\n");
        String actualPostfix = outputLines[0].trim();
        double actualResult = Double.parseDouble(outputLines[1].trim());

        assertEquals(expectedPostfix.toString(), actualPostfix);
        assertEquals(expectedResult, actualResult, 0.000001);
    }

}
