package ua.edu.duikt.finance;
import java.util.*;
public abstract class TransactionReportGenerator {
 public static void printBalanceReport(double balance) { System.out.printf("Загальний баланс: %.2f грн%n",balance); }
 public static void printTransactionsCountByMonth(String month,int count) { System.out.println("Кількість транзакцій за "+month+": "+count); }
 public static void printTopExpensesReport(List<Transaction> ts) { System.out.println("10 найбільших витрат:"); ts.forEach(System.out::println); }
 public static void printExtreme(String label,Optional<Transaction> t) { System.out.println(label+": "+t.map(Transaction::toString).orElse("Немає витрат")); }
 public static void printExpenseReport(String title,Map<String,Double> data) { System.out.println(title+" (1 * = 1000 грн, повні тисячі)"); data.forEach((k,v)->System.out.printf("%s: %.2f грн %s%n",k,v,"*".repeat((int)(v/1000)))); }
}
