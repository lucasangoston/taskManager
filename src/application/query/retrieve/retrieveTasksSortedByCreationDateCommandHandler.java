package application.query.retrieve;

import domain.model.Task;
import kernel.query.QueryHandler;

import java.util.List;

public class retrieveTasksSortedByCreationDateCommandHandler implements QueryHandler<retrieveTasksSortedByCreationDate, List<Task>> {

    @Override
    public List<Task> handle(retrieveTasksSortedByCreationDate query) {
        return null;
    }
}
