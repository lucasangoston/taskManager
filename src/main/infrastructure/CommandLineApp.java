package main.infrastructure;

import main.application.command.create.CreateTask;
import main.application.command.create.CreateTaskHandler;
import main.application.command.delete.DeleteTask;
import main.application.command.delete.DeleteTaskHandler;
import main.application.command.update.UpdateTask;
import main.application.command.update.UpdateTaskHandler;
import main.application.query.retrieve.RetrieveTasksSortedByCreationDate;
import main.application.query.retrieve.RetrieveTasksSortedByCreationDateHandler;
import main.domain.model.State;
import main.kernel.FileHandler;
import main.kernel.exception.EmptyFileException;
import main.kernel.exception.InvalidCommandLineException;
import main.kernel.exception.SaveToFileException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommandLineApp {

    private String commandLine;
    private String[] commandArgs;
    private FileHandler fileHandler;
    private String description;
    private Date dueDate;
    private State state;

    public CommandLineApp(String command, FileHandler fileHandler) {
        this.commandLine = command;
        this.commandArgs = command.split(" ");
        this.fileHandler = fileHandler;
        this.description = "";
        this.dueDate = new Date();
        this.state = State.TODO;
    }

    public void run() throws Exception {
        if (!commandArgs[0].equals("agenda")) {
            throw new InvalidCommandLineException("Invalid command. Usage: agenda <create|update|remove|list>");
        }
        switch (commandArgs[1].toLowerCase()) {
            case "create":
                updateTaskValues(this.commandLine);
                createTask();
                break;
            case "update":
                updateTaskValues(this.commandLine);
                updateTask();
                break;
            case "remove":
                removeTask();
                break;
            case "list":
                listTasks();
                break;
            default:
                throw new InvalidCommandLineException("Invalid command. Usage: agenda <create|update|remove|list>");
        }
    }

    private void updateTaskValues(String commandLine) throws Exception {
        Pattern commandPattern = Pattern.compile("(\\w+)");
        Matcher matcher = commandPattern.matcher(commandLine);

        if (!matcher.find()) {
            throw new InvalidCommandLineException("Invalid command. Usage: agenda <create|update> -c <description> [-d:<dueDate>] [-s:<state>]");
        }

        Pattern optionPattern = Pattern.compile("-([cds]):([^\\s\"]+|\"[^\"]+\")");
        matcher = optionPattern.matcher(commandLine);

        while (matcher.find()) {
            String option = matcher.group(1);
            String value = matcher.group(2);

            if (option.equals("c")) {
                this.description = extractOptionValue(value);
            } else if (option.equals("d")) {
                this.dueDate = getDueDate(extractOptionValue(value));
            } else if (option.equals("s")) {
                this.state = getState(extractOptionValue(value));
            }
        }
    }
    private String extractOptionValue(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        } else {
            return value;
        }
    }

    private Date getDueDate(String dueDate) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
    }
    private State getState(String state) {
        switch (state.toLowerCase()) {
            case "todo":
                return State.TODO;
            case "pending":
                return State.PENDING;
            case "progress":
                return State.PROGRESS;
            case "done":
                return State.DONE;
            case "cancelled":
                return State.CANCELLED;
            case "closed":
                return State.CLOSED;
            default:
                throw new IllegalArgumentException("Invalid state. Please enter a valid state.");
        }
    }

    private void createTask() throws Exception {
        CreateTaskHandler createTaskHandler = new CreateTaskHandler(fileHandler);
        CreateTask task = new CreateTask(this.dueDate, null, this.description, this.state);
        createTaskHandler.handle(task);
    }

    private void updateTask() throws Exception {
        if (commandArgs.length <= 3) {
            throw new InvalidCommandLineException("Invalid command. Usage: agenda remove <taskId>");
        }
        String taskId = commandArgs[2];
        if (!taskId.matches("\\d+")) {
            throw new NumberFormatException("Invalid taskId. Please enter a valid number.");
        }
        UpdateTaskHandler updateTaskHandler = new UpdateTaskHandler(fileHandler);
        UpdateTask task = new UpdateTask(UUID.fromString(taskId), this.dueDate, null, this.description, this.state);
        updateTaskHandler.handle(task);
    }

    private void removeTask() throws Exception {
        if (commandArgs.length != 3) {
            throw new InvalidCommandLineException("Invalid command. Usage: agenda remove <taskId>");
        }
        String taskId = commandArgs[2];
        if (!taskId.matches("\\d+")) {
            throw new NumberFormatException("Invalid taskId. Please enter a valid number.");
        }
        DeleteTaskHandler deleteTaskHandler = new DeleteTaskHandler(fileHandler);
        deleteTaskHandler.handle(new DeleteTask(UUID.fromString(taskId)));
    }

    private void listTasks() throws Exception {
        RetrieveTasksSortedByCreationDateHandler retrieveTasksHandler = new RetrieveTasksSortedByCreationDateHandler(fileHandler, fileHandler.getTasks());
        RetrieveTasksSortedByCreationDate retrieveTasksSortedByCreationDate = new RetrieveTasksSortedByCreationDate();
        System.out.println(retrieveTasksHandler.handle(retrieveTasksSortedByCreationDate));
    }
}
