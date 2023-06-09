package main.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import main.domain.model.Task;
import main.kernel.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JsonFileHandler implements FileHandler {
    private final ObjectMapper objectMapper;
    static final String filePath = "/Users/lucasangoston/Downloads/taskManager/src/main/consoleagenda/data.json";

    public JsonFileHandler() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public List<Task> getTasks() {
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Task>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Task> getTask(UUID taskId) {
        return null;
    }

    @Override
    public void refreshTasks(List<Task> tasks) {
        try {
            objectMapper.writeValue(new File(filePath), tasks);
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    @Override
    public void deleteTask(UUID taskId) {
        List<Task> existingTasks = getTasks();

        boolean isRemoved = existingTasks.removeIf(task -> task.getId().equals(taskId));
        if (!isRemoved) {
            System.out.println("Task not found.");
            return;
        }

        refreshTasks(existingTasks);
        System.out.println("Task deleted successfully.");
    }

    @Override
    public void addTasks(List<Task> tasks) {
        List<Task> existingTasks = getTasks();
        existingTasks.addAll(tasks);
        refreshTasks(existingTasks);
    }

    @Override
    public void updateTask(Task task) throws Exception {
        List<Task> existingTasks = getTasks();

        int index = existingTasks.indexOf(task);

        if (index == -1) {
            throw new Exception("Task not found.");
        }

        existingTasks.set(index, task);
        refreshTasks(existingTasks);
    }
}
