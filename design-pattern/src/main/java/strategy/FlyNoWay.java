package strategy;

/**
 * Created by Lankidd on 2017/3/23.
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Can't fly...");
    }
}
