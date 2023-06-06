package kernel;

public interface CommandHandler<C extends Command> {
    void handle(C command);
}