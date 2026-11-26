package ua.edu.duikt.automaton;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        TestAutomaton a = new TestAutomaton();
        for (String s : new String[]{"abcTESTabc", "abcTES", "TTEST", "TETEST", "abc", ""}) {
            System.out.printf("\"%s\" -> початковий: %s, виправлений: %s%n", s, a.recognizeOriginal(s), a.recognize(s));
        }
        System.out.println("Введіть рядок:");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) System.out.println("Кінцевий стан: " + a.recognize(scanner.nextLine()));
    }
}
