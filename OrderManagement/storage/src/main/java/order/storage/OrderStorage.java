package order.storage;
import java.util.List; import order.processing.Product; import order.storage.internal.MemoryStore;
public final class OrderStorage { private final MemoryStore store=new MemoryStore(); public void save(Product p){store.add(p);} public List<Product> getOrders(){return store.snapshot();} }