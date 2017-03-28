package state;

/**
 * Created by Lankidd on 2017/3/26.
 */
public class GumballMachine{
    private State soldOutState;
    private State noQuarterState;
    private State soldState;
    private State hasQuarterState;
    private State WinnerState;

    private State state = soldOutState;

    private int number = 0;

    public GumballMachine(int number) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        soldState = new SoldState(this);
        hasQuarterState = new HasQuarterState(this);
        WinnerState = new WinnerState(this);
        if (number > 0) {
            this.number = number;
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
    }

    public void dispense() {
        state.dispense();
    }

    void releaseBall() {
        if ( number > 0) {
            number--;
            System.out.println("release ball");
        }
    }

    public void refillBall(int number) {
        if (number > 0) {
            if (state == soldOutState) {
                state = noQuarterState;
            }
            this.number += number;
        }
    }

    void setState(State state) {
        this.state = state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getWinnerState() {
        return WinnerState;
    }

    public int getNumber() {
        return number;
    }
}
