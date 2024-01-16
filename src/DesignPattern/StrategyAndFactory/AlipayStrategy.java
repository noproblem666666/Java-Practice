package DesignPattern.StrategyAndFactory;

// 具体支付策略类：支付宝支付
public class AlipayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用支付宝支付：" + amount + "元");
        // 具体的支付逻辑
    }
}
