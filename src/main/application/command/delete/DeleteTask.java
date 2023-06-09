package main.application.command.delete;

import main.kernel.command.Command;

import java.util.UUID;

public class DeleteTask implements Command {
    public final UUID id;

    public DeleteTask(UUID id) {
        this.id = id;
    }
}
