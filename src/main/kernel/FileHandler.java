package main.kernel;

import main.domain.model.Task;
import main.kernel.exception.EmptyFileException;
import main.kernel.exception.SaveToFileException;
import main.kernel.exception.TaskNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileHandler {
    List<Task> getTasks() throws EmptyFileException;

    Optional<Task> getTask(UUID taskId) throws TaskNotFoundException, EmptyFileException;
    void refreshTasks(List<Task> task) throws SaveToFileException;
    boolean deleteTask(UUID taskId) throws Exception;
    void addTasks(List<Task> tasks) throws SaveToFileException, EmptyFileException;
    void updateTask(Task task) throws Exception;
}
