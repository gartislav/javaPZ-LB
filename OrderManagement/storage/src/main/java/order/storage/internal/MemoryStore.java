package order.storage.internal;
import java.util.*; import java.util.concurrent.ConcurrentLinkedQueue; import order.processing.Product;
public final class MemoryStore { private final Queue<Product> items=new ConcurrentLinkedQueue<>(); public void add(Product p){items.add(p);} public List<Product> snapshot(){return List.copyOf(items);} }