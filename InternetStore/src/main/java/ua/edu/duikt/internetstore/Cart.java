package ua.edu.duikt.internetstore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    public boolean removeProductById(int productId) {
        return products.removeIf(product -> product.getId() == productId);
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        if (products.isEmpty()) {
            return "Кошик порожній.";
        }

        StringBuilder result = new StringBuilder("Кошик містить:\n");
        for (Product product : products) {
            result.append(product).append(System.lineSeparator());
        }
        result.append(String.format("Загальна вартість: %.2f грн", getTotalPrice()));
        return result.toString();
    }
}
