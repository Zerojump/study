package decorator;

/**
 * Created by Lankidd on 2017/3/25.
 */
public abstract class Beverage {
    protected String description = "unknown beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

    @Override
    public String toString() {
        return "Beverage{" +
                "description='" + description + '\'' +
                '}';
    }
}
