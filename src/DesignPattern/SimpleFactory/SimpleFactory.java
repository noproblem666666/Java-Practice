package DesignPattern.SimpleFactory;

public class SimpleFactory {
    //静态方法，这样可以不用新建对象直接调用
    public static Product createProduct(String type){
        if(type.equals("A")){
            return new ConcreteProductA();
        }else if(type.equals("B")){
            return new ConcreteProductB();
        }else{
            throw new IllegalArgumentException("无效的产品类型");
        }
    }
}
