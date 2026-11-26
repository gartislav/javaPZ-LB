package ua.edu.duikt.calculator;

import java.util.Scanner;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) { run(scanner, System.out); }
    }
    public static void run(Scanner scanner, PrintStream out) {
        Calculator calculator = new Calculator();
        try {
            out.println("Введіть перше число:");
            double a = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            out.println("Введіть друге число:");
            double b = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            out.println("Оберіть операцію (+, -, *, /, sqrt; sqrt використовує перше число):");
            String operation = scanner.nextLine().trim();
            double result = switch (operation) {
                case "+" -> calculator.add(a, b);
                case "-" -> calculator.subtract(a, b);
                case "*" -> calculator.multiply(a, b);
                case "/" -> calculator.divide(a, b);
                case "sqrt" -> calculator.sqrt(a);
                default -> throw new InvalidInputException("Невідома операція.");
            };
            out.println("Результат: " + result);
        } catch (NumberFormatException e) {
            out.println("Помилка: введіть коректне число.");
        } catch (ArithmeticException | InvalidInputException e) {
            out.println("Помилка: " + e.getMessage());
        } catch (NoSuchElementException e) {
            out.println("Помилка: недостатньо вхідних даних.");
        } finally {
            out.println("Обробку запиту завершено.");
        }
    }
}
