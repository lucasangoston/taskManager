package main.kernel.command;

public interface CommandHandler<C extends Command> {
    void handle(C command);
}
