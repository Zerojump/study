package strategy;

/**
 * Created by Lankidd on 2017/3/23.
 */
public class FlyWithWing implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Fly with wings...");
    }
}
