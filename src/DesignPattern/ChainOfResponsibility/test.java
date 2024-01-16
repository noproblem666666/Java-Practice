package DesignPattern.ChainOfResponsibility;

public class test {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler(10);
        Handler handler2 = new ConcreteHandler(20);
        Handler handler3 = new ConcreteHandler(30);
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        handler1.handleRequest(15);
        handler1.handleRequest(25);
        handler1.handleRequest(35);
    }
}
