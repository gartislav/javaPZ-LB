package ua.edu.duikt.finance;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.time.*;
import java.util.stream.*;
class TransactionAnalyzerTest {
 @Test void testCalculateTotalBalance() { assertEquals(200.0,TransactionAnalyzer.calculateTotalBalance(List.of(new Transaction("01-01-2023",100,"Дохід"),new Transaction("02-01-2023",-50,"Витрата"),new Transaction("03-01-2023",150,"Дохід"))),0.001); }
 @Test void testCountTransactionsByMonth() { var ts=List.of(new Transaction("01-02-2023",50,"Дохід"),new Transaction("15-02-2023",-20,"Витрата"),new Transaction("05-03-2023",100,"Дохід")); assertEquals(2,TransactionAnalyzer.countTransactionsByMonth(ts,"02-2023")); assertEquals(1,TransactionAnalyzer.countTransactionsByMonth(ts,"03-2023")); }
 @Test void testTopExpenses() { var ts=new ArrayList<>(IntStream.rangeClosed(1,12).mapToObj(i->new Transaction("01-01-2024",-i,"Витрата")).toList()); ts.add(new Transaction("01-01-2024",100,"Дохід")); var top=TransactionAnalyzer.findTopExpenses(ts); assertEquals(10,top.size()); assertEquals(-12,top.getFirst().getAmount(),0.001); assertEquals(-3,top.getLast().getAmount(),0.001); }
 @Test void testExtremes() { var ts=List.of(new Transaction("01-01-2024",-100,"A"),new Transaction("31-01-2024",-20,"A"),new Transaction("01-02-2024",-500,"B")); var a=LocalDate.of(2024,1,1); var b=a.plusMonths(1).minusDays(1); assertEquals(-100,TransactionAnalyzer.findLargestExpense(ts,a,b).orElseThrow().getAmount(),0.001); assertEquals(-20,TransactionAnalyzer.findSmallestExpense(ts,a,b).orElseThrow().getAmount(),0.001); assertEquals(120,TransactionAnalyzer.expensesByCategory(ts).get("A"),0.001); }
}
