package kernel;

import domain.model.Task;

import java.util.List;
import java.util.UUID;

public interface Repository <T>{
    List<T> getAll();
    T getById(UUID id);
    void add(Task task);
    void update(Task task);
    void delete(Task task);
}
