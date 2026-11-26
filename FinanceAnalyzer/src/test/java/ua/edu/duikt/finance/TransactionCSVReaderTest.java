package ua.edu.duikt.finance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
class TransactionCSVReaderTest {
 @TempDir Path dir;
 @Test void testReadCSV() throws Exception { var file=dir.resolve("test.csv"); Files.writeString(file,"Дата,Сума,Категорія\n05-12-2023,-450,Сільпо\n10-12-2023,8000,Зарплата\n"); var ts=TransactionCSVReader.readTransactions(file.toString()); assertEquals(2,ts.size()); assertEquals(-450,ts.getFirst().getAmount(),0.001); assertEquals("Сільпо",ts.getFirst().getDescription()); }
}
