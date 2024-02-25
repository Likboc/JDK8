package design.behavioral.stragety;

/**
 * 策略模式
 * 继承同一策略接口，编写不同实现类完成
 * 又名政策模式
 * 包括：Context，Strategy Interface和 Strategy impl
 */
public interface Strategy {
    void run();
}
