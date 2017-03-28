package command.receiver;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class FanReceiver {
    public static final int HIGH = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;

    private String id;
    private int speed;

    public FanReceiver(String id) {
        this.id = id;
        speed = OFF;
        System.out.println("make a fan with id is " + id + " and speed " + speed);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        System.out.println(id + "'s speed is going to be " + speed);
    }
}
