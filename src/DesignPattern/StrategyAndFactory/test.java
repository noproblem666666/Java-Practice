package DesignPattern.StrategyAndFactory;

public class test {
    public static void main(String[] args) {
        // 创建支付策略工厂
        PaymentStrategyFactory factory = new PaymentStrategyFactory();

        // 使用支付宝支付
        PaymentStrategy alipayStrategy = factory.createPaymentStrategy("alipay");
        alipayStrategy.pay(100.0);

        // 使用微信支付
        PaymentStrategy wechatPayStrategy = factory.createPaymentStrategy("wechatpay");
        wechatPayStrategy.pay(200.0);
    }
}
