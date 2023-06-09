package main.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Task {
    @JsonProperty("id")
    private final UUID id;

    @JsonProperty("dateTime")
    private final Date dateTime;

    @JsonProperty("dueDate")
    private final Date dueDate;

    @JsonProperty("closeDate")
    private final Date closeDate;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("state")
    private final State state;

    @JsonProperty("subTasks")
    private final List<Task> subTasks;

    public Task(UUID id, Date dateTime, Date dueDate, Date closeDate, String description, State state, List<Task> subTasks) {
        this.id = id;
        this.dateTime = dateTime;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.description = description;
        this.state = state;
        this.subTasks = subTasks;
    }

    @JsonCreator
    public static Task jsonCreator(
            @JsonProperty("id") UUID id,
            @JsonProperty("dateTime") Date dateTime,
            @JsonProperty("dueDate") Date dueDate,
            @JsonProperty("closeDate") Date closeDate,
            @JsonProperty("description") String description,
            @JsonProperty("state") State state,
            @JsonProperty("subTasks") List<Task> subTasks
    ) {
        if (id == null) {
            id = UUID.randomUUID();
        }
        return new Task(id, dateTime, dueDate, closeDate, description, state, subTasks);
    }


    public UUID getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public State getState() {
        return state;
    }

    public List<Task> getSubTasks() {
        return subTasks;
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Task && ((Task) obj).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        System.out.println("Task.hashCode");
        System.out.println("this = " + this.getId().hashCode());
        return this.getId().hashCode();
    }
}
