package command.command;

import command.receiver.LightReceiver;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class LightOnCommand implements Command {
    private LightReceiver lightReceiver;

    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        lightReceiver.on();
    }

    @Override
    public void undo() {
        lightReceiver.off();
    }
}
