package command.command;

import command.receiver.FanReceiver;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class FanLowCommand extends AbstractFanCommand {
    public FanLowCommand(FanReceiver fanReceiver) {
        super(fanReceiver);
    }

    @Override
    public void execute() {
        fanReceiver.setSpeed(FanReceiver.LOW);
    }
}
