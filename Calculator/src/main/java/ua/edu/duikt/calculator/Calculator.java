package ua.edu.duikt.calculator;

public class Calculator {
    private void validate(double... values) throws InvalidInputException {
        for (double value : values) {
            if (!Double.isFinite(value)) throw new InvalidInputException("Число має бути скінченним.");
        }
    }
    public double add(double a, double b) throws InvalidInputException {
        validate(a, b); return a + b;
    }
    public double subtract(double a, double b) throws InvalidInputException {
        validate(a, b); return a - b;
    }
    public double multiply(double a, double b) throws InvalidInputException {
        validate(a, b); return a * b;
    }
    public double divide(double a, double b) throws InvalidInputException {
        validate(a, b);
        if (b == 0.0) throw new ArithmeticException("Ділення на нуль неможливе.");
        return a / b;
    }
    public double sqrt(double a) throws InvalidInputException {
        validate(a);
        if (a < 0) throw new InvalidInputException("Квадратний корінь з від’ємного числа неможливий.");
        return Math.sqrt(a);
    }
}
