package order.threads;
import com.github.javafaker.Faker; import java.util.*; import order.processing.*; import order.storage.*;
public class Main {
 public static void main(String[] args) throws Exception {
 Faker faker=new Faker(); OrderStorage storage=new OrderStorage(); List<OrderProcessor<? extends Product>> orders=new ArrayList<>();
 for(int i=0;i<5;i++){
 orders.add(new OrderProcessor<>(Electronics.builder().name(faker.commerce().productName()).brand(faker.company().name()).price(faker.number().numberBetween(1000,20000)).build()));
 orders.add(new OrderProcessor<>(Clothing.builder().name(faker.commerce().productName()).size("M").price(faker.number().numberBetween(100,3000)).build()));
 }
 orders.add(new OrderProcessor<>(Clothing.builder().name("Invalid").price(-1).size("S").build()));
 new OrderThreadManager(storage).processAll(orders);
 List<Product> saved=storage.getOrders(); System.out.println("Збережено замовлень: "+saved.size());
 System.out.println("Електроніка: "+saved.stream().filter(Electronics.class::isInstance).count());
 System.out.println("Одяг: "+saved.stream().filter(p -> p instanceof Clothing).count());
 saved.stream().map(Product::getName).forEach(System.out::println);
 if(saved.size()!=10)throw new IllegalStateException("Очікується 10 замовлень");
 }
}