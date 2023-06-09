package main.kernel;

import main.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileHandler {
    List<Task> getTasks();

    Optional<Task> getTask(UUID taskId);
    void refreshTasks(List<Task> task);
    void deleteTask(UUID taskId) throws Exception;
    void addTasks(List<Task> tasks);
    void updateTask(Task task) throws Exception;
}
