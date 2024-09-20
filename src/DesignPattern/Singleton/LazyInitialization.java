package DesignPattern.Singleton;
//懒汉式单例模式
public class LazyInitialization {
    private static LazyInitialization instance;

    private LazyInitialization(){
        //私有构造方法，防止外部创建实例
    }

    //在多线程环境下可能出现竞争条件，通过synchronized关键字来保证线程安全
    public static synchronized LazyInitialization getInstance(){
        if(instance==null){
            instance = new LazyInitialization();
        }
        return instance;
    }
}





