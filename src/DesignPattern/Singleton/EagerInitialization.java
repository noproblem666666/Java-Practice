package DesignPattern.Singleton;
//饿汉式单例模式
public class EagerInitialization {
    //确保只有一个示例被创建和共享
    private static final EagerInitialization instance = new EagerInitialization();

    private EagerInitialization(){
        //私有构造方法，防止外部实例化
    }

    //返回示例对象
    public static EagerInitialization getInstance(){
        return instance;
    }
}
