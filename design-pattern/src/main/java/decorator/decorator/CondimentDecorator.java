package decorator.decorator;

import decorator.Beverage;

/**
 * Created by Lankidd on 2017/3/25.
 */
public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();

    @Override
    public String toString() {
        return "CondimentDecorator{" + getDescription() + "}";
    }
}
