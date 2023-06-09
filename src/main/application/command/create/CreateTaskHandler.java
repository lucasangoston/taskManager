package main.application.command.create;

import main.domain.model.Task;
import main.kernel.FileHandler;
import main.kernel.command.CommandHandler;

import java.util.ArrayList;
import java.util.List;

public class CreateTaskHandler implements CommandHandler<CreateTask> {
    private final FileHandler fileHandler;

    public CreateTaskHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void handle(CreateTask createTask) {
        Task task = new Task(createTask.id, createTask.dateTime, createTask.dueDate, createTask.closeDate, createTask.description, createTask.state, createTask.subTasks);
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        fileHandler.addTasks(tasks);
    }
}
