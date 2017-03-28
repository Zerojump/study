package command.command;

import java.util.Arrays;

/**
 * Created by Lankidd on 2017/3/25.
 */
public class MacroCommand implements Command {
    private Command[] commands;

    public MacroCommand(Command... commands) {
        this.commands = commands != null ? commands : new Command[]{new NoCommand()};
    }

    @Override
    public void execute() {
        Arrays.stream(commands).forEach(Command::execute);
    }

    @Override
    public void undo() {
        Arrays.stream(commands).forEach(Command::undo);
    }
}
