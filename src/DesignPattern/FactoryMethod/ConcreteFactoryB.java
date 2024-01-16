package DesignPattern.FactoryMethod;

public class ConcreteFactoryB implements Factory{

    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}
