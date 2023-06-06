package domain.repository;

import domain.model.Task;
import kernel.Repository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends Repository<Task> {
    List<Task> getAll();
    Task getById(UUID id);
    void add(Task task);

     void update(Task task);

     void delete(Task task);
}
