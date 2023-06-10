import main.application.command.create.CreateTaskHandler;
import main.application.command.delete.DeleteTaskHandler;
import main.application.command.update.UpdateTaskHandler;
import main.application.query.retrieve.RetrieveTasksSortedByCreationDate;
import main.application.query.retrieve.RetrieveTasksSortedByCreationDateHandler;
import main.domain.model.State;
import main.domain.model.Task;
import main.infrastructure.CommandLineApp;
import main.infrastructure.CsvFileHandler;
import main.kernel.FileHandler;
import main.kernel.exception.EmptyFileException;
import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String commandLine = scanner.nextLine();
        scanner.close();

        FileHandler fileHandler = new CsvFileHandler(CSVFormat.DEFAULT,"src/main/consoleagenda/data.csv");
        CommandLineApp commandLineApp = new CommandLineApp(commandLine, fileHandler);

        commandLineApp.run();

        UpdateTaskHandler updateTaskHandler = new UpdateTaskHandler(fileHandler);
        DeleteTaskHandler deleteTaskHandler = new DeleteTaskHandler(fileHandler);
        RetrieveTasksSortedByCreationDateHandler retrieveTasksSortedByCreationDateHandler = new RetrieveTasksSortedByCreationDateHandler(fileHandler, fileHandler.getTasks());
        CreateTaskHandler createTaskHandler = new CreateTaskHandler(fileHandler);

        UUID id = UUID.randomUUID();
        Task task = new Task(id, new Date(), new Date(), new Date(), "description", State.TODO, new ArrayList<>());

        RetrieveTasksSortedByCreationDate retrieveTasksSortedByCreationDate = new RetrieveTasksSortedByCreationDate();
        //System.out.println(retrieveTasksSortedByCreationDateHandler.handle(retrieveTasksSortedByCreationDate));

    }
}
