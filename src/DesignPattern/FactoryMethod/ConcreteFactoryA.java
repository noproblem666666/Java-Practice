package DesignPattern.FactoryMethod;
//具体工厂类，使用到了多态
public class ConcreteFactoryA implements Factory{
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}
