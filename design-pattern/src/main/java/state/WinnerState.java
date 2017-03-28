package state;

/**
 * Created by Lankidd on 2017/3/26.
 */
public class WinnerState extends SoldOutState {

    public WinnerState(GumballMachine GumBallMachine) {
        super(GumBallMachine);
    }

    @Override
    public void dispense() {
        GumballMachine ballMachine = getBallMachine();
        ballMachine.releaseBall();
        if (ballMachine.getNumber() == 0) {
            ballMachine.setState(ballMachine.getSoldOutState());
        } else {
            System.out.println("you win!");
            ballMachine.releaseBall();
            if (ballMachine.getNumber() > 0) {
                ballMachine.setState(ballMachine.getNoQuarterState());
            } else {
                ballMachine.setState(ballMachine.getSoldOutState());
            }
        }
    }
}
