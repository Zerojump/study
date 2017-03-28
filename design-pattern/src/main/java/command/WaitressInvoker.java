package command;

import command.command.Command;
import command.command.NoCommand;

import java.util.Arrays;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class WaitressInvoker {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    public WaitressInvoker(int num) {
        onCommands = new Command[num];
        Arrays.stream(onCommands).forEach(c -> c = new NoCommand());
        offCommands = new Command[num];
        Arrays.stream(offCommands).forEach(c -> c = new NoCommand());
        System.out.println("make a waitressInvoker with " + num + " commands");
    }

    public void setOnCommands(int slot, Command onCommand, Command offCommand) {
        if (slot >= onCommands.length) {
            throw new IllegalArgumentException();
        }
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onCommandPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void offCommandPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void undoCommandPushed() {
        undoCommand.execute();
    }
}
