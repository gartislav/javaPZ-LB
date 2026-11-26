package ua.edu.duikt.internetstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Product> CATALOG = new ArrayList<>();
    private static final List<Order> ORDER_HISTORY = new ArrayList<>();
    private static final Cart CART = new Cart();

    public static void main(String[] args) {
        initializeCatalog();
        System.out.println("Вітаємо в навчальному інтернет-магазині!");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Ваш вибір: ");

            switch (choice) {
                case 1 -> showCatalog();
                case 2 -> addToCart();
                case 3 -> showCart();
                case 4 -> removeFromCart();
                case 5 -> checkout();
                case 6 -> showOrderHistory();
                case 7 -> searchProducts();
                case 0 -> {
                    running = false;
                    System.out.println("Дякуємо, що скористалися нашим магазином!");
                }
                default -> System.out.println("Невідома опція. Спробуйте ще раз.");
            }
        }
    }

    private static void initializeCatalog() {
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");
        Category clothing = new Category(4, "Одяг");

        CATALOG.add(new Product(1, "Ноутбук", 19999.99,
                "Високопродуктивний ноутбук для роботи та навчання", electronics));
        CATALOG.add(new Product(2, "Смартфон", 12999.50,
                "Смартфон з великим екраном та високою автономністю", smartphones));
        CATALOG.add(new Product(3, "Навушники", 2499.00,
                "Бездротові навушники з шумозаглушенням", accessories));
        CATALOG.add(new Product(4, "Футболка", 599.00,
                "Бавовняна повсякденна футболка", clothing));
    }

    private static void printMenu() {
        System.out.println("\n========== МЕНЮ ==========");
        System.out.println("1 - Переглянути каталог товарів");
        System.out.println("2 - Додати товар до кошика");
        System.out.println("3 - Переглянути кошик");
        System.out.println("4 - Видалити товар із кошика");
        System.out.println("5 - Оформити замовлення");
        System.out.println("6 - Історія замовлень");
        System.out.println("7 - Пошук товарів");
        System.out.println("0 - Вийти");
    }

    private static void showCatalog() {
        System.out.println("\n--- Каталог товарів ---");
        CATALOG.forEach(System.out::println);
    }

    private static void addToCart() {
        showCatalog();
        int id = readInt("Введіть ID товару для додавання: ");
        Product product = findById(id);
        if (product == null) {
            System.out.println("Товар з таким ID не знайдено.");
            return;
        }
        CART.addProduct(product);
        System.out.println("Товар додано до кошика: " + product.getName());
    }

    private static void showCart() {
        System.out.println("\n--- Ваш кошик ---");
        System.out.println(CART);
    }

    private static void removeFromCart() {
        if (CART.getProducts().isEmpty()) {
            System.out.println("Кошик порожній.");
            return;
        }
        showCart();
        int id = readInt("Введіть ID товару для видалення: ");
        if (CART.removeProductById(id)) {
            System.out.println("Товар(и) з ID " + id + " видалено з кошика.");
        } else {
            System.out.println("У кошику немає товару з таким ID.");
        }
    }

    private static void checkout() {
        if (CART.getProducts().isEmpty()) {
            System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
            return;
        }
        Order order = new Order(CART);
        ORDER_HISTORY.add(order);
        System.out.println("\nЗамовлення оформлено:");
        System.out.println(order);
        CART.clear();
    }

    private static void showOrderHistory() {
        System.out.println("\n--- Історія замовлень ---");
        if (ORDER_HISTORY.isEmpty()) {
            System.out.println("Історія замовлень порожня.");
            return;
        }
        ORDER_HISTORY.forEach(order -> {
            System.out.println(order);
            System.out.println("-------------------------");
        });
    }

    private static void searchProducts() {
        System.out.println("1 - Пошук за назвою");
        System.out.println("2 - Пошук за категорією");
        int choice = readInt("Оберіть тип пошуку: ");
        System.out.print("Введіть пошуковий запит: ");
        String query = SCANNER.nextLine().trim().toLowerCase();

        List<Product> results = CATALOG.stream()
                .filter(product -> {
                    if (choice == 1) {
                        return product.getName().toLowerCase().contains(query);
                    }
                    if (choice == 2) {
                        return product.getCategory().getName().toLowerCase().contains(query);
                    }
                    return false;
                })
                .toList();

        if (results.isEmpty()) {
            System.out.println("За вашим запитом нічого не знайдено.");
        } else {
            System.out.println("--- Результати пошуку ---");
            results.forEach(System.out::println);
        }
    }

    private static Product findById(int id) {
        return CATALOG.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Будь ласка, введіть ціле число.");
            }
        }
    }
}
