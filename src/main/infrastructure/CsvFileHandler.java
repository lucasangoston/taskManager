package main.infrastructure;

import main.domain.model.Task;
import org.apache.commons.csv.CSVParser;

import java.util.List;
import java.util.UUID;

public class CsvFileHandler implements FileHandler{

    private final CSVParser csvParser;

    public CsvFileHandler(CSVParser csvParser) {
        this.csvParser = csvParser;
    }

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
