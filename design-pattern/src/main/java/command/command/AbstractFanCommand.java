package command.command;

import command.receiver.FanReceiver;

/**
 * Created by Lankidd on 2017/3/25.
 */
public abstract class AbstractFanCommand implements Command {
    private int preSpeed;
    protected FanReceiver fanReceiver;

    public AbstractFanCommand(FanReceiver fanReceiver) {
        this.fanReceiver = fanReceiver;
        preSpeed = FanReceiver.OFF;
    }

    public void setPreSpeedAndExecute() {
        this.preSpeed = fanReceiver.getSpeed();
        execute();
    }

    @Override
    public void undo() {
        fanReceiver.setSpeed(preSpeed);
    }
}