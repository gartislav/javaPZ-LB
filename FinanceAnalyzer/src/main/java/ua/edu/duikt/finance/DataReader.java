package ua.edu.duikt.finance;
import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
public abstract class DataReader {
 public static Reader open(String source) throws IOException {
  if(source.startsWith("https://") || source.startsWith("http://")) {
   var connection = URI.create(source).toURL().openConnection();
   connection.setConnectTimeout(5000); connection.setReadTimeout(5000);
   return new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
  }
  return Files.newBufferedReader(Path.of(source), StandardCharsets.UTF_8);
 }
}
