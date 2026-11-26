package order.threads;
import java.util.*; import java.util.concurrent.*; import order.processing.*; import order.storage.*;
public final class OrderThreadManager {
 private final OrderStorage storage;
 public OrderThreadManager(OrderStorage storage){this.storage=storage;}
 private void processOrder(OrderProcessor<? extends Product> processor){try{storage.save(processor.process());}catch(InvalidOrderException e){System.out.println("Помилка: "+e.getMessage());}}
 public void processAll(List<OrderProcessor<? extends Product>> orders) throws InterruptedException, ExecutionException {
 try(ExecutorService pool=Executors.newFixedThreadPool(3)){
 List<Future<?>> tasks=new ArrayList<>(); orders.forEach(order -> tasks.add(pool.submit(() -> processOrder(order))));
 for(Future<?> task:tasks)task.get();
 }
 }
}