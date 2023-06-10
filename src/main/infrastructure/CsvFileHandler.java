package main.infrastructure;

import main.domain.model.State;
import main.domain.model.Task;
import main.kernel.FileHandler;
import main.kernel.exception.TaskNotFoundException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvFileHandler implements FileHandler {
    private final CSVFormat csvFormat;
    private final DateFormat dateFormat;

    public CsvFileHandler(CSVFormat csvFormat) {
        this.csvFormat = csvFormat;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    @Override
    public boolean deleteTask(UUID taskId) throws Exception {
        List<Task> tasks = getTasks();

        boolean removed = tasks.removeIf(task -> task.getId().equals(taskId));

        if (removed) {
            refreshTasks(tasks);

        }

        return removed;
    }


    @Override
    public void addTasks(List<Task> tasks) {
        List<Task> taskList = getTasks();
        taskList.addAll(tasks);
        refreshTasks(taskList);
    }

    @Override
    public void updateTask(Task task) throws Exception {
        List<Task> existingTasks = getTasks();

        int index = existingTasks.indexOf(task);
        System.out.println(existingTasks);

        if (index == -1) {
            throw new TaskNotFoundException("Task not found.");
        }

        existingTasks.set(index, task);
        refreshTasks(existingTasks);
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/consoleagenda/data.csv"))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                UUID id = UUID.fromString(fields[0]);
                Date dateTime = dateFormat.parse(fields[1]);
                Date dueDate = dateFormat.parse(fields[2]);
                Date closeDate = dateFormat.parse(fields[3]);
                String description = fields[4];
                State state = State.valueOf(fields[5]);

                Task task = new Task(id, dateTime, dueDate, closeDate, description, state, new ArrayList<>());
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return tasks;
    }

    @Override
    public Optional<Task> getTask(UUID taskId) {
        return getTasks()
                .stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst();
    }

    @Override
    public void refreshTasks(List<Task> tasks) {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter("src/main/consoleagenda/data.csv"), csvFormat)) {
            for (Task task : tasks) {
                csvPrinter.printRecord(
                        task.getId(),
                        dateFormat.format(task.getDateTime()),
                        dateFormat.format(task.getDueDate()),
                        dateFormat.format(task.getCloseDate()),
                        task.getDescription(),
                        task.getState(),
                        task.getSubTasks()
                );
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}