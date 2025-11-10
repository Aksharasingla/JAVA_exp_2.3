// ===== Part A: EmployeeSorting.java =====
import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Name: %-10s Age: %-3d Salary: %.2f", name, age, salary);
    }
}

public class EmployeeSorting {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Aarav", 25, 45000),
            new Employee("Neha", 30, 60000),
            new Employee("Riya", 22, 40000),
            new Employee("Vikram", 28, 55000)
        );

        System.out.println("--- Sort by Name ---");
        employees.sort((e1, e2) -> e1.name.compareToIgnoreCase(e2.name));
        employees.forEach(System.out::println);

        System.out.println("\n--- Sort by Age (Ascending) ---");
        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        employees.forEach(System.out::println);

        System.out.println("\n--- Sort by Salary (Descending) ---");
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        employees.forEach(System.out::println);
    }
}


// ===== Part B: StudentStreamFilter.java =====
import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f)", name, marks);
    }
}

public class StudentStreamFilter {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Aarav", 85),
            new Student("Neha", 72),
            new Student("Riya", 90),
            new Student("Karan", 60),
            new Student("Simran", 78)
        );

        System.out.println("Students scoring above 75%, sorted by marks:");

        students.stream()
                .filter(s -> s.marks > 75)
                .sorted((s1, s2) -> Double.compare(s1.marks, s2.marks))
                .map(s -> s.name)
                .forEach(System.out::println);
    }
}


// ===== Part C: ProductStreamOperations.java =====
import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %.2f", name, category, price);
    }
}

public class ProductStreamOperations {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 85000, "Electronics"),
            new Product("Phone", 60000, "Electronics"),
            new Product("Shirt", 2000, "Clothing"),
            new Product("Jeans", 3500, "Clothing"),
            new Product("Mixer", 4000, "Home"),
            new Product("Fridge", 25000, "Home")
        );

        // Group by category
        System.out.println("\n--- Products Grouped by Category ---");
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));
        grouped.forEach((cat, list) -> {
            System.out.println(cat + ":");
            list.forEach(p -> System.out.println("  " + p));
        });

        // Find most expensive product in each category
        System.out.println("\n--- Most Expensive Product in Each Category ---");
        Map<String, Optional<Product>> maxPrice = products.stream()
                .collect(Collectors.groupingBy(p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));

        maxPrice.forEach((cat, prod) -> System.out.println(cat + " -> " + prod.orElse(null)));

        // Calculate average price of all products
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));

        System.out.printf("\nAverage price of all products: %.2f\n", avgPrice);
    }
}

/*
Compilation and Execution:
1. Save all classes in one file or separate files with matching names.
2. Compile with:
   javac EmployeeSorting.java StudentStreamFilter.java ProductStreamOperations.java
3. Run each:
   java EmployeeSorting
   java StudentStreamFilter
   java ProductStreamOperations

Demonstrates:
- Part A: Lambda-based sorting (Collections.sort / List.sort)
- Part B: Filtering, sorting, and mapping using streams
- Part C: Grouping, max, and average using Stream & Collectors
*/
