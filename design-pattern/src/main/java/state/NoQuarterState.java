package state;

/**
 * Created by Lankidd on 2017/3/26.
 */
public class NoQuarterState extends State {
    public NoQuarterState(GumballMachine ballMachine) {
        super(ballMachine);
    }

    @Override
    public void insertQuarter() {
        GumballMachine ballMachine = getBallMachine();
        ballMachine.setState(ballMachine.getHasQuarterState());
        System.out.println("insert a quater,please turn crank,or you can eject quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("you haven't ejected any quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("please insert a quarter first");
    }

    @Override
    public void dispense() {
        System.out.println("please insert a quarter first");
    }
}
