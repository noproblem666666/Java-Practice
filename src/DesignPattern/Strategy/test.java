package DesignPattern.Strategy;

//只要代码中有冗长的if else或switch，都可以使用策略模式进行改进
//通常策略类使用@Component注解来让spring容器管理
//配合工厂模式，工厂根据传递的不同参数来返回不同的策略对象
public class test {
    public static void main(String[] args) {
        Order order = new Order();

        //设置支付宝支付策略
        order.setPaymentStrategy(new AlipayStrategy());
        double totalPaymentAmount = order.calculateTotalPayment(1000);
        System.out.println("支付宝支付金额：" + totalPaymentAmount);

        //设置微信支付策略
        order.setPaymentStrategy(new WechatPayStrategy());
        totalPaymentAmount = order.calculateTotalPayment(1000);
        System.out.println("微信支付金额：" + totalPaymentAmount);
    }
}
