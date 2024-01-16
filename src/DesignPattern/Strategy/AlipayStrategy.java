package DesignPattern.Strategy;
//具体策略类
public class AlipayStrategy implements PaymentStrategy {
    @Override
    public double calculatePaymentAmount(double orderAmount) {
        return orderAmount * 0.9;
    }
}
