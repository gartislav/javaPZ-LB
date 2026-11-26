package ua.edu.duikt.internetstore;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private static int nextId = 1;

    private final int id;
    private final List<Product> products;
    private final double totalPrice;
    private String status;
    private final LocalDateTime createdAt;

    public Order(Cart cart) {
        this.id = nextId++;
        this.products = new ArrayList<>(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове";
        this.createdAt = LocalDateTime.now();
    }

    public void setStatus(String status) {
        if (status != null && !status.isBlank()) {
            this.status = status;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Замовлення №").append(id).append(System.lineSeparator());
        for (Product product : products) {
            result.append(" - ").append(product.getName())
                    .append(" — ").append(String.format("%.2f грн", product.getPrice()))
                    .append(System.lineSeparator());
        }
        result.append(String.format("Загальна вартість: %.2f грн", totalPrice))
                .append(System.lineSeparator())
                .append("Статус: ").append(status)
                .append(System.lineSeparator())
                .append("Створено: ")
                .append(createdAt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        return result.toString();
    }
}
