package state;

/**
 * Created by Lankidd on 2017/3/26.
 */
public class SoldState extends State {
    public SoldState(GumballMachine ballMachine) {
        super(ballMachine);
    }

    @Override
    public void insertQuarter() {
        System.out.println("in sold,please wait");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("can't eject,because it's in sold");
    }

    @Override
    public void turnCrank() {
        System.out.println("you can't turn twice");
    }

    @Override
    public void dispense() {
        GumballMachine ballMachine = getBallMachine();
        ballMachine.releaseBall();
        if (ballMachine.getNumber() > 0) {
            ballMachine.setState(ballMachine.getNoQuarterState());
        } else {
            ballMachine.setState(ballMachine.getSoldOutState());
        }
    }
}
