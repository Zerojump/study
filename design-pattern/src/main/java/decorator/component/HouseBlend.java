package decorator.component;

import decorator.Beverage;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "house blend";
    }

    @Override
    public double cost() {
        return 0.98;
    }
}
