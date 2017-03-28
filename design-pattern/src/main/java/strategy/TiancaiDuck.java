package strategy;

/**
 * Created by Lankidd on 2017/3/23.
 */
public class TiancaiDuck extends Duck {
    public TiancaiDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new GaGaQuack();
        System.out.println("Make a TiancaiDuck");
    }
}