package DesignPattern.Strategy;

public class WechatPayStrategy implements PaymentStrategy{
    @Override
    public double calculatePaymentAmount(double orderAmount) {
        return orderAmount;
    }
}
