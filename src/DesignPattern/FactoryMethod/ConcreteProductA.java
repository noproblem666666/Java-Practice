package DesignPattern.FactoryMethod;

public class ConcreteProductA implements Product{

    @Override
    public void use() {
        System.out.println("使用具体产品A");
    }
}
