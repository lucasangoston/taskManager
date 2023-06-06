package main.infrastructure;

import main.domain.model.Task;

import java.util.List;
import java.util.UUID;

public class TextFileHandler implements FileHandler{
    @Override
    public void deleteTasks(UUID taskId) {

    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public void saveTasks(List<Task> task) {

    }
}
