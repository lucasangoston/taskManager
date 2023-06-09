package main.application.command.delete;

import main.kernel.FileHandler;
import main.kernel.command.CommandHandler;

public class DeleteTaskHandler implements CommandHandler<DeleteTask> {
    private final FileHandler fileHandler;

    public DeleteTaskHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void handle(DeleteTask deleteTask) throws Exception {
        fileHandler.deleteTask(deleteTask.id);
    }
}
