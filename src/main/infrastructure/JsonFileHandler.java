package main.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import main.domain.model.Task;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class JsonFileHandler implements FileHandler {
    private final ObjectMapper objectMapper;

    public JsonFileHandler() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public List<Task> getTasks() {
        try {
            return objectMapper.readValue(new File("/Users/lucasangoston/Downloads/taskManager/src/main/consoleagenda/data.json"), new TypeReference<List<Task>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void saveTasks(List<Task> tasks) {
        try {
            objectMapper.writeValue(new File("data.json"), tasks);
        } catch (IOException e) {

        }
    }

    @Override
    public void deleteTasks(UUID taskId) {
        List<Task> tasks = getTasks();
        if (tasks != null) {
            tasks.removeIf(task -> task.getTaskId().equals(taskId));
            saveTasks(tasks);
        }
    }


}
