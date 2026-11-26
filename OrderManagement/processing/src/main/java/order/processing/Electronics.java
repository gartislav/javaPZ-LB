package order.processing;
import lombok.Builder;
import lombok.Getter;
@Getter public final class Electronics extends Product {
 private final String brand;
 @Builder public Electronics(String name,double price,String brand){super(name,price);this.brand=brand;}
}