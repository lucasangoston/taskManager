package main.infrastructure;

import main.domain.model.Task;

import java.util.List;
import java.util.UUID;

public interface FileHandler {
    List<Task> getTasks();
    void saveTasks(List<Task> task);
    void deleteTasks(UUID taskId);
}
