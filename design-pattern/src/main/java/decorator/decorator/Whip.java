package decorator.decorator;

import decorator.Beverage;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class Whip extends CondimentDecorator {
    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",whip";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.5;
    }
}
