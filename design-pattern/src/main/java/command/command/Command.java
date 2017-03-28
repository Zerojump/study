package command.command;

/**
 * Created by Lankidd on 2017/3/25.
 */
public interface Command {
    void execute();
    void undo();
}
