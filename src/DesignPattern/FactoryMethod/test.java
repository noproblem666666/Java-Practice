package DesignPattern.FactoryMethod;

public class test {
    //测试工厂方法模式
    public static void main(String[] args) {

        Factory factoryA = new ConcreteFactoryA();
        Product productA = factoryA.createProduct();
        productA.use();

        Factory factoryB = new ConcreteFactoryB();
        Product productB = factoryB.createProduct();
        productB.use();

    }
}
