package ua.edu.duikt.finance;
import java.io.*;
import java.util.*;
public abstract class TransactionCSVReader {
 public static List<Transaction> readTransactions(String source) throws IOException {
  try (Reader reader = DataReader.open(source)) { return parse(reader); }
 }
 public static List<Transaction> parse(Reader reader) throws IOException {
  var result = new ArrayList<Transaction>();
  var br = new BufferedReader(reader); String line;
  while ((line=br.readLine()) != null) {
   line=line.replace("\uFEFF", "").strip();
   if(line.isEmpty() || line.startsWith("Дата,") || line.toLowerCase(Locale.ROOT).startsWith("date,")) continue;
   String[] v=line.split(",",3);
   if(v.length!=3) throw new IOException("Некоректний рядок: "+line);
   try { result.add(new Transaction(v[0].strip(), Double.parseDouble(v[1].strip()), v[2].strip())); }
   catch(NumberFormatException e) { throw new IOException("Некоректна сума: "+line,e); }
  }
  return result;
 }
}
