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
import main.kernel.exception.InvalidCommandLineException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommandLineApp {

    private FileHandler fileHandler;
    private String description;
    private Date dueDate;
    private State state;

    public CommandLineApp(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.description = "";
        this.dueDate = null;
        this.state = null;
    }

    public void usage() {
        System.out.println("Usage:");
        System.out.println("\tagenda <create|update|remove|list>");
        System.out.println("\tagenda <create> [-c:<description>] [-d:<yyyy-MM-dd>] [-s:<todo|pending|progress|done|cancelled|closed>]");
        System.out.println("\tagenda update <id> -c:<description> [-d:<yyyy-MM-dd>] [-s:<todo|pending|progress|done|cancelled|closed>]");
        System.out.println("\tagenda remove <id>");
    }

    public void run(String commandLine) throws Exception {
        String[] commandArgs = commandLine.split(" ");
        if (!commandArgs[0].equals("agenda")) {
            throw new InvalidCommandLineException("Invalid command. Usage: agenda <create|update|remove|list>");
        }
        switch (commandArgs[1].toLowerCase()) {
            case "create":
                updateTaskValues(commandLine);
                createTask();
                break;
            case "update":
                updateTaskValues(commandLine);
                updateTask(commandArgs);
                break;
            case "remove":
                removeTask(commandArgs);
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
            throw new InvalidCommandLineException("Invalid command. Usage: agenda <create|update> -c:<description> [-d:<dueDate>] [-s:<state>]");
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

    private void updateTask(String[] commandArgs) throws Exception {
        if (commandArgs.length <= 3) {
            throw new InvalidCommandLineException("Invalid command. Usage: agenda update <taskId> -c:<description> [-d:<dueDate>] [-s:<state>]");
        }
        String taskId = commandArgs[2];
        UpdateTaskHandler updateTaskHandler = new UpdateTaskHandler(fileHandler);
        UpdateTask task = new UpdateTask(UUID.fromString(taskId), this.dueDate, null, this.description, this.state);
        updateTaskHandler.handle(task);
    }

    private void removeTask(String[] commandArgs) throws Exception {
        if (commandArgs.length != 3) {
            throw new InvalidCommandLineException("Invalid command. Usage: agenda remove <taskId>");
        }
        String taskId = commandArgs[2];
        DeleteTaskHandler deleteTaskHandler = new DeleteTaskHandler(fileHandler);
        deleteTaskHandler.handle(new DeleteTask(UUID.fromString(taskId)));
    }

    private void listTasks() throws Exception {
        RetrieveTasksSortedByCreationDateHandler retrieveTasksHandler = new RetrieveTasksSortedByCreationDateHandler(fileHandler, fileHandler.getTasks());
        RetrieveTasksSortedByCreationDate retrieveTasksSortedByCreationDate = new RetrieveTasksSortedByCreationDate();
        System.out.println(retrieveTasksHandler.handle(retrieveTasksSortedByCreationDate));
    }
}
