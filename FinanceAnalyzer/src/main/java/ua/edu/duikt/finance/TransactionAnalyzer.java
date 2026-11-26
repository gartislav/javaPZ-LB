package ua.edu.duikt.finance;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;
public abstract class TransactionAnalyzer {
 private static final DateTimeFormatter DATE=DateTimeFormatter.ofPattern("dd-MM-uuuu");
 private static LocalDate date(Transaction t) { return LocalDate.parse(t.getDate(),DATE); }
 public static double calculateTotalBalance(List<Transaction> ts) { return ts.stream().mapToDouble(Transaction::getAmount).sum(); }
 public static int countTransactionsByMonth(List<Transaction> ts,String month) { var m=YearMonth.parse(month,DateTimeFormatter.ofPattern("MM-uuuu")); return (int)ts.stream().filter(t->YearMonth.from(date(t)).equals(m)).count(); }
 public static List<Transaction> findTopExpenses(List<Transaction> ts) { return ts.stream().filter(t->t.getAmount()<0).sorted(Comparator.comparingDouble(Transaction::getAmount)).limit(10).toList(); }
 private static Stream<Transaction> expenses(List<Transaction> ts,LocalDate from,LocalDate to) { if(from.isAfter(to)) throw new IllegalArgumentException("Некоректний період"); return ts.stream().filter(t->t.getAmount()<0 && !date(t).isBefore(from) && !date(t).isAfter(to)); }
 public static Optional<Transaction> findLargestExpense(List<Transaction> ts,LocalDate from,LocalDate to) { return expenses(ts,from,to).min(Comparator.comparingDouble(Transaction::getAmount)); }
 public static Optional<Transaction> findSmallestExpense(List<Transaction> ts,LocalDate from,LocalDate to) { return expenses(ts,from,to).max(Comparator.comparingDouble(Transaction::getAmount)); }
 public static Map<String,Double> expensesByCategory(List<Transaction> ts) { return ts.stream().filter(t->t.getAmount()<0).collect(Collectors.groupingBy(Transaction::getDescription,TreeMap::new,Collectors.summingDouble(t->-t.getAmount()))); }
 public static Map<String,Double> expensesByMonth(List<Transaction> ts) { return ts.stream().filter(t->t.getAmount()<0).collect(Collectors.groupingBy(t->YearMonth.from(date(t)).toString(),TreeMap::new,Collectors.summingDouble(t->-t.getAmount()))); }
}
