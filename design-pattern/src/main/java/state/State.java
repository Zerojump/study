package state;

/**
 * Created by Lankidd on 2017/3/26.
 */
public abstract class State {
    private GumballMachine ballMachine;

    public State(GumballMachine ballMachine) {
        this.ballMachine = ballMachine;
    }

    public void insertQuarter() {

    }

    public void ejectQuarter() {

    }

    public void turnCrank() {

    }

    public void dispense() {

    }

    public GumballMachine getBallMachine() {
        return ballMachine;
    }
}
