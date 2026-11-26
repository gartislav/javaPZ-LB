package ua.edu.duikt.finance;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;
public class Main {
 public static void main(String[] args) throws IOException {
  String source=args.length>0?args[0]:"https://informer.com.ua/dut/java/pr2.csv";
  List<Transaction> ts;
  try { ts=TransactionCSVReader.readTransactions(source); }
  catch(IOException e) { if(args.length>0) throw e; System.out.println("URL недоступний. Використано демонстраційний CSV."); ts=TransactionCSVReader.readTransactions("sample.csv"); }
  ts.forEach(System.out::println);
  String month=args.length>1?args[1]:"01-2024";
  LocalDate from=args.length>2?LocalDate.parse(args[2]):LocalDate.of(2023,12,1);
  LocalDate to=args.length>3?LocalDate.parse(args[3]):LocalDate.of(2024,1,31);
  TransactionReportGenerator.printBalanceReport(TransactionAnalyzer.calculateTotalBalance(ts));
  TransactionReportGenerator.printTransactionsCountByMonth(month,TransactionAnalyzer.countTransactionsByMonth(ts,month));
  TransactionReportGenerator.printTopExpensesReport(TransactionAnalyzer.findTopExpenses(ts));
  System.out.println("Період: "+from+" — "+to);
  TransactionReportGenerator.printExtreme("Найбільша витрата",TransactionAnalyzer.findLargestExpense(ts,from,to));
  TransactionReportGenerator.printExtreme("Найменша витрата",TransactionAnalyzer.findSmallestExpense(ts,from,to));
  TransactionReportGenerator.printExpenseReport("Витрати за категоріями",TransactionAnalyzer.expensesByCategory(ts));
  TransactionReportGenerator.printExpenseReport("Витрати за місяцями",TransactionAnalyzer.expensesByMonth(ts));
 }
}
