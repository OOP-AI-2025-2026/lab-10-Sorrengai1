package ua.opnu;

import ua.opnu.util.Customer;
import ua.opnu.util.DataProvider;
import ua.opnu.util.Order;
import ua.opnu.util.Product;

import java.util.*;
import java.util.stream.Collectors;

public class HardTasks {

    private final List<Customer> customers = DataProvider.customers;
    private final List<Order> orders = DataProvider.orders;
    private final List<Product> products = DataProvider.products;

    public static void main(String[] args) {
        HardTasks tasks = new HardTasks();

        // Для того, щоб побачити в консолі результат роботи методу, разкоментуйте відповідний рядок коду

        // Завдання 1
        Objects.requireNonNull(tasks.getBooksWithPrice(),"Method getBooksWithPrice() returns null").forEach(System.out::println);

        // Завдання 2
        Objects.requireNonNull(tasks.getOrdersWithBabyProducts(),"Method getOrdersWithBabyProducts() returns null").forEach(System.out::println);

        // Завдання 3
        Objects.requireNonNull(tasks.applyDiscountToToys(),"Method applyDiscountToToys() returns null").forEach(System.out::println);
        // Завдання 4
        System.out.println(Objects.requireNonNull(tasks.getCheapestBook(),"Method getCheapestBook() returns null").get());

        // Завдання 5
        Objects.requireNonNull(tasks.getRecentOrders(),"Method getRecentOrders() returns null").forEach(System.out::println);

        // Завдання 6
        DoubleSummaryStatistics statistics = Objects.requireNonNull(tasks.getBooksStats(), "Method getBooksStats() returns null");
        System.out.printf("count = %1$d, average = %2$f, max = %3$f, min = %4$f, sum = %5$f%n", statistics.getCount(), statistics.getAverage(), statistics.getMax(), statistics.getMin(), statistics.getSum());

        // Завдання 7
        Objects.requireNonNull(tasks.getOrdersProductsMap(),"Method getOrdersProductsMap() returns null").forEach((id, size) -> System.out.printf("%1$d : %2$d\n", id, size));

        // Завдання 8
        Objects.requireNonNull(tasks.getProductsByCategory(), "Method getProductsByCategory() returns null").forEach((name, list) -> System.out.printf("%1$s : %2$s\n", name, Arrays.toString(list.toArray())));
    }

    public List<Product> getBooksWithPrice() {
        return products.stream()
                .filter(p -> "Books".equals(p.getCategory()) && p.getPrice() > 100)
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersWithBabyProducts() {
        return orders.stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> "Baby".equals(product.getCategory())))
                .collect(Collectors.toList());
    }

    public List<Product> applyDiscountToToys() {
        return products.stream()
                .filter(p -> "Toys".equals(p.getCategory()))
                .peek(p -> p.setPrice(p.getPrice() * 0.5))
                .collect(Collectors.toList());
    }

    public Optional<Product> getCheapestBook() {
        return products.stream()
                .filter(product -> "Books".equals(product.getCategory()))
                .min(Comparator.comparing(Product::getPrice));
    }

    public List<Order> getRecentOrders() {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public DoubleSummaryStatistics getBooksStats() {
        return products.stream()
                .filter(product -> "Books".equals(product.getCategory()))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();
    }

    public Map<Integer, Integer> getOrdersProductsMap() {
        return orders.stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        order -> order.getProducts().size()
                ));
    }

    public Map<String, List<Integer>> getProductsByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getId, Collectors.toList())
                ));
    }
}
