package state;

/**
 * Created by Lankidd on 2017/3/26.
 */
public class SoldOutState extends State {
    public SoldOutState(GumballMachine GumBallMachine) {
        super(GumBallMachine);
    }

    @Override
    public void insertQuarter() {
        System.out.println("sold out and eject your quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("sold out...");
    }

    @Override
    public void turnCrank() {
        System.out.println("sold out...");
    }

    @Override
    public void dispense() {
        System.out.println("sold out...");
    }
}
