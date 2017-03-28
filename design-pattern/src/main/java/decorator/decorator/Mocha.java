package decorator.decorator;

import decorator.Beverage;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class Mocha extends CondimentDecorator {
    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mocha";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }
}
