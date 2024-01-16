package DesignPattern.SimpleFactory;

public class test {
    public static void main(String[] args) {
        Product productA = SimpleFactory.createProduct("A");
        productA.use();
        Product productB = SimpleFactory.createProduct("B");
        productB.use();

    }
}
