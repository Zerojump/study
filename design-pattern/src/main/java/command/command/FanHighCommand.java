package command.command;

import command.receiver.FanReceiver;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class FanHighCommand extends AbstractFanCommand {
    public FanHighCommand(FanReceiver fanReceiver) {
        super(fanReceiver);
    }

    @Override
    public void execute() {
        fanReceiver.setSpeed(FanReceiver.HIGH);
    }
}
