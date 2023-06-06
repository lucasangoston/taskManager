package main.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Task {
    private final UUID id;
    private final Date dateTime;
    private final Date dueDate;
    private final Date closeDate;

    private final String description;
    private final State state;

    private final List<Task> subTasks;


    @JsonCreator
    public Task(
            @JsonProperty("dateTime") Date dateTime,
            @JsonProperty("dueDate") Date dueDate,
            @JsonProperty("closeDate") Date closeDate,
            @JsonProperty("description") String description,
            @JsonProperty("state") State state,
            @JsonProperty("subTasks") List<Task> subTasks
    ) {
        this.id = UUID.randomUUID();
        this.dateTime = dateTime;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.description = description;
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

    public String getDescription() { return description;}

    public State getState() {
        return state;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public Object getTaskId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", dueDate=" + dueDate +
                ", closeDate=" + closeDate +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", subTasks=" + subTasks +
                '}';
    }
}
