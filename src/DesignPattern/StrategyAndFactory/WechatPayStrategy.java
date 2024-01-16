package DesignPattern.StrategyAndFactory;

// 具体支付策略类：微信支付
public class WechatPayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付：" + amount + "元");
        // 具体的支付逻辑
    }
}
