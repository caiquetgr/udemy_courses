package com.activemesa.solid.ocp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE
}

@Getter
@AllArgsConstructor
public class Product {

    private String name;
    private Color color;
    private Size size;

}

class ProductFilter {

    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> color.equals(p.getColor()));
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> size.equals(p.getSize()));
    }

    public Stream<Product> filterByColorAndSize(List<Product> products,
                                                Size size,
                                                Color color) {
        return products.stream().filter(p -> size.equals(p.getSize()) && color.equals(p.getColor()));
    }

}

class Demo {

    public static void main(String[] args) {

        List<Product> products = List.of(new Product("Apple", Color.RED, Size.MEDIUM),
                new Product("Tree", Color.GREEN, Size.LARGE),
                new Product("House", Color.BLUE, Size.MEDIUM));

        ProductFilter productFilter = new ProductFilter();
        System.out.println("Green products (old): ");

        productFilter.filterByColor(products, Color.GREEN)
                .forEach(p -> System.out.println(p.getName() + " is green"));

        BetterFilter betterFilter = new BetterFilter();
        System.out.println("Green products (new): ");

        betterFilter.filter(products, new ColorSpecification(Color.GREEN))
                .forEach(p -> System.out.println(p.getName() + " is green"));

        System.out.println("Blue and medium products (new): ");

        betterFilter.filter(products,
                new AndSpecification(
                        new ColorSpecification(Color.BLUE),
                        new SizeSpecification(Size.MEDIUM)))
                .forEach(p -> System.out.println(p.getName() + " is blue and medium"));

        System.out.println("Blue and medium products (AndAll): ");

        betterFilter.filter(products,
                new AndAllSpecification(List.of(
                        new ColorSpecification(Color.BLUE),
                        new SizeSpecification(Size.MEDIUM))))
                .forEach(p -> System.out.println(p.getName() + " is blue and medium"));
    }

}

interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> specification);
}

@AllArgsConstructor
class ColorSpecification implements Specification<Product> {

    private Color color;

    @Override
    public boolean isSatisfied(Product item) {
        return color.equals(item.getColor());
    }

}

@AllArgsConstructor
class SizeSpecification implements Specification<Product> {

    private Size size;

    @Override
    public boolean isSatisfied(Product item) {
        return size.equals(item.getSize());
    }

}

@AllArgsConstructor
class AndSpecification implements Specification<Product> {

    Specification<Product> first, second;

    @Override
    public boolean isSatisfied(Product item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }

}

@AllArgsConstructor
class AndAllSpecification implements Specification<Product> {

    private List<Specification<Product>> specifications;

    @Override
    public boolean isSatisfied(Product item) {
        return specifications.stream().allMatch(spec -> spec.isSatisfied(item));
    }

}

class BetterFilter implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> specification) {
        return items.stream().filter(i -> specification.isSatisfied(i));
    }

}
