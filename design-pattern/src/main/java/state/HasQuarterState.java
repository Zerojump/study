package state;

import java.util.Random;

/**
 * Created by Lankidd on 2017/3/26.
 */
public class HasQuarterState extends State {
    private Random random = new Random(System.currentTimeMillis());

    public HasQuarterState(GumballMachine ballMachine) {
        super(ballMachine);
    }

    @Override
    public void insertQuarter() {
        System.out.println("you don't need insert again");
    }

    @Override
    public void ejectQuarter() {
        GumballMachine ballMachine = getBallMachine();
        ballMachine.setState(ballMachine.getNoQuarterState());
        System.out.println("eject a quarter");
    }

    @Override
    public void turnCrank() {
        GumballMachine ballMachine = getBallMachine();
        System.out.println("you turn crank");
        int i = random.nextInt(2);
        if (i == 0 && ballMachine.getNumber() > 1) {
            ballMachine.setState(ballMachine.getWinnerState());
        } else {
            ballMachine.setState(ballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("please turn crank first");
    }
}
