package ua.edu.duikt.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class CalculatorTest {
    private final Calculator calculator = new Calculator();
    @Test void addition() throws InvalidInputException { assertEquals(8.0, calculator.add(5, 3)); }
    @Test void subtraction() throws InvalidInputException { assertEquals(2.0, calculator.subtract(5, 3)); }
    @Test void multiplication() throws InvalidInputException { assertEquals(15.0, calculator.multiply(5, 3)); }
    @Test void division() throws InvalidInputException { assertEquals(2.5, calculator.divide(5, 2)); }
    @Test void divisionByZero() { assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0)); }
    @Test void squareRoot() throws InvalidInputException { assertEquals(3.0, calculator.sqrt(9)); }
    @Test void negativeSquareRoot() { assertThrows(InvalidInputException.class, () -> calculator.sqrt(-9)); }
    @Test void nonFiniteInput() { assertThrows(InvalidInputException.class, () -> calculator.add(Double.NaN, 2)); }
    @Test void invalidTextInput() {
        String output = run("abc\n2\n+\n");
        assertTrue(output.contains("введіть коректне число"));
        assertTrue(output.contains("Обробку запиту завершено."));
    }
    @Test void unknownOperation() { assertTrue(run("1\n2\n?\n").contains("Невідома операція")); }
    @Test void zeroDivisionInMain() {
        String output = run("5\n0\n/\n");
        assertTrue(output.contains("Ділення на нуль"));
        assertTrue(output.contains("Обробку запиту завершено."));
    }
    private String run(String input) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (Scanner scanner = new Scanner(input); PrintStream out = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            Main.run(scanner, out);
        }
        return bytes.toString(StandardCharsets.UTF_8);
    }
}
