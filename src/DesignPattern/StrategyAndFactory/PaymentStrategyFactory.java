package DesignPattern.StrategyAndFactory;

// 支付策略工厂
public class PaymentStrategyFactory {
    public static PaymentStrategy createPaymentStrategy(String paymentMethod) {
        if (paymentMethod.equalsIgnoreCase("alipay")) {
            return new AlipayStrategy();
        } else if (paymentMethod.equalsIgnoreCase("wechatpay")) {
            return new WechatPayStrategy();
        }
        throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
    }
}
