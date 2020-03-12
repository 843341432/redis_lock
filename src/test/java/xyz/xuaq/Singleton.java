package xyz.xuaq;

public class Singleton {
    private volatile static Singleton singleton;

    public Singleton() {
    }

    public static Singleton getSinleton(){
        if (singleton==null){
            synchronized (Singleton.class){
                if (singleton==null){
                    singleton=new Singleton();
                }
            }
        }
        return singleton;
    }
}
