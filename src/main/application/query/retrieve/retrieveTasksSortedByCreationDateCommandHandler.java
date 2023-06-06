package main.application.query.retrieve;

import main.domain.model.Task;
import main.kernel.query.QueryHandler;

import java.util.List;

public class retrieveTasksSortedByCreationDateCommandHandler implements QueryHandler<retrieveTasksSortedByCreationDate, List<Task>> {

    @Override
    public List<Task> handle(retrieveTasksSortedByCreationDate query) {
        return null;
    }
}
