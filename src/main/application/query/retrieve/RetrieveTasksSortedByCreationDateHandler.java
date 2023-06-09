package main.application.query.retrieve;

import main.domain.model.Task;
import main.kernel.FileHandler;
import main.kernel.query.QueryHandler;

import java.util.Comparator;
import java.util.List;

public class RetrieveTasksSortedByCreationDateHandler implements QueryHandler<RetrieveTasksSortedByCreationDate, List<Task>> {
    private final FileHandler fileHandler;
    private final List<Task> tasks;


    public RetrieveTasksSortedByCreationDateHandler(FileHandler fileHandler, List<Task> tasks) {
        this.fileHandler = fileHandler;
        this.tasks = tasks;
    }

    @Override
    public List<Task> handle(RetrieveTasksSortedByCreationDate query) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDateTime))
                .toList();
    }
}
