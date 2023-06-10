package main.application.command.delete;

import main.kernel.FileHandler;
import main.kernel.command.CommandHandler;
import main.kernel.exception.TaskNotFoundException;

public class DeleteTaskHandler implements CommandHandler<DeleteTask> {
    private final FileHandler fileHandler;

    public DeleteTaskHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void handle(DeleteTask deleteTask) throws Exception {
        boolean isRemoved = fileHandler.deleteTask(deleteTask.id);

        if (!isRemoved) throw new TaskNotFoundException("Task not found");
    }
}
