package kernel;

import domain.model.Task;

import java.util.List;

public interface Repository <T>{
    List<T> getAll();
    void add(Task task);
    void update(Task task);
    void delete(Task task);
}
