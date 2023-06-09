package main.application.command.update;

import main.domain.model.State;
import main.domain.model.Task;
import main.kernel.command.Command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UpdateTask implements Command {
    public final UUID id;
    public final Date dueDate;
    public final Date closeDate;
    public final String description;
    public final State state;
    public final List<Task> subTasks;
    public UpdateTask(UUID id, Date dueDate, Date closeDate, String description, State state) {
        this.id = id;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.description = description;
        this.state = state;
        this.subTasks = new ArrayList<>();
    }
}
