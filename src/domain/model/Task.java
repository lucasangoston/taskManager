package domain.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Task {
    private final UUID id;
    private final Date dateTime;
    private final Date dueDate;
    private final Date closeDate;
    private final State state;

    private final List<Task> subTasks;

    public Task(Date dateTime, Date dueDate, Date closeDate, State state, List<Task> subTasks) {
        this.id = UUID.randomUUID();
        this.dateTime = dateTime;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.state = state;
        this.subTasks = subTasks;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public State getState() {
        return state;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }
}
