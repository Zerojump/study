package decorator.component;

import decorator.Beverage;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
