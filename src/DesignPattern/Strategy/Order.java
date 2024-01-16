package DesignPattern.Strategy;
//上下文类
public class Order {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public double calculateTotalPayment(double orderAmount){
        return paymentStrategy.calculatePaymentAmount(orderAmount);
    }
}
