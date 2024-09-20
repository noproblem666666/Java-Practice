package DesignPattern.Singleton;
//静态内部类（Bill Pugh Singleton）： 利用静态内部类的特性实现单例，线程安全且高效。
public class BillPughSingleton {
    private BillPughSingleton() {}

    private static class SingletonHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        //Todo：静态内部类只会在使用的时候加载一次，不存在线程安全问题
        return SingletonHolder.INSTANCE;
    }
}