package DesignPattern.Strategy;
//定义策略接口
public interface PaymentStrategy {
    double calculatePaymentAmount(double orderAmount);
}
