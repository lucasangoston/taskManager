package main.application.command.update;

import main.domain.model.Task;
import main.kernel.FileHandler;
import main.kernel.command.CommandHandler;

import java.util.Optional;

public class UpdateTaskHandler implements CommandHandler<UpdateTask> {
    private final FileHandler fileHandler;

    public UpdateTaskHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void handle(UpdateTask updateTask) throws Exception {
        Optional<Task> getOldTask = fileHandler.getTask(updateTask.id);

        if (getOldTask.isPresent()) {
            Task taskUpdated = new Task(updateTask.id, getOldTask.get().getDateTime(), updateTask.dueDate, updateTask.closeDate, updateTask.description, updateTask.state, updateTask.subTasks);

            fileHandler.updateTask(taskUpdated);
        }

        throw new Exception("Task not found");
    }
}