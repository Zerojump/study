package command.receiver;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class LightReceiver {
    private String id;

    public LightReceiver(String id) {
        this.id = id;
        System.out.println("make a light with id is " + id + " and status off");
    }

    public void on() {
        System.out.println(id + " is going to be turned on");
    }

    public void off() {
        System.out.println(id + " is going to be turned off");
    }
}
