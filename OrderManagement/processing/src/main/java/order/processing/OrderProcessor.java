package order.processing;
public final class OrderProcessor<T extends Product> {
 private final T product;
 public OrderProcessor(T product){this.product=product;}
 public T process() throws InvalidOrderException {
 if(product==null || product.getName()==null || product.getName().isBlank() || !Double.isFinite(product.getPrice()) || product.getPrice()<=0) throw new InvalidOrderException("Некоректний товар");
 System.out.printf("[%s] Оброблено: %s, %.2f грн%n",Thread.currentThread().getName(),product.getName(),product.getPrice()); return product;
 }
}