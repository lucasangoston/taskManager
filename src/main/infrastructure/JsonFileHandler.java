package main.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import main.domain.model.Task;
import main.kernel.FileHandler;
import main.kernel.exception.EmptyFileException;
import main.kernel.exception.SaveToFileException;
import main.kernel.exception.TaskNotFoundException;

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
    public List<Task> getTasks() throws EmptyFileException {
        try {
            File file = new File(filePath);
            if (file.length() == 0) {
                throw new EmptyFileException("File is empty");
            }
            return objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Task> getTask(UUID taskId) throws TaskNotFoundException, EmptyFileException {
        try {
            List<Task> tasks = getTasks();
            if (tasks.isEmpty()) {
                throw new EmptyFileException("File is empty");
            }
            return tasks.stream()
                    .filter(task -> task.getId().equals(taskId))
                    .findFirst();
        } catch (Exception e) {
            throw new TaskNotFoundException("Task not found");
        }
    }


    @Override
    public void refreshTasks(List<Task> tasks) throws SaveToFileException {
        try {
            objectMapper.writeValue(new File(filePath), tasks);
        } catch (IOException e) {
            throw new SaveToFileException("Error while saving to file.");
        }
    }

    @Override
    public boolean deleteTask(UUID taskId) throws SaveToFileException, EmptyFileException {
        List<Task> existingTasks = getTasks();

        boolean isRemoved = existingTasks.removeIf(task -> task.getId().equals(taskId));

        if (isRemoved) {
            refreshTasks(existingTasks);
        }

        return isRemoved;
    }

    @Override
    public void addTasks(List<Task> tasks) throws SaveToFileException, EmptyFileException {
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
