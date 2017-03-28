package state;

/**
 * Created by Lankidd on 2017/3/26.
 */
public interface BallMachine {
    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();

    void releaseBall();

    void refillBall();

    void setState(State state);

    int getNumber();

    void setNumber(int number);
}
