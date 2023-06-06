package infrastructure;

import domain.model.Task;
import domain.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

public class InMemoryTaskRepository implements TaskRepository {
    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public Task getById(UUID id) {
        return null;
    }

    @Override
    public void add(Task task) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(Task task) {

    }
}
