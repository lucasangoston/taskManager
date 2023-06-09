import main.application.command.create.CreateTask;
import main.application.command.create.CreateTaskHandler;
import main.application.command.delete.DeleteTask;
import main.application.command.delete.DeleteTaskHandler;
import main.application.command.update.UpdateTask;
import main.application.command.update.UpdateTaskHandler;
import main.application.query.retrieve.RetrieveTasksSortedByCreationDate;
import main.application.query.retrieve.RetrieveTasksSortedByCreationDateHandler;
import main.domain.model.State;
import main.domain.model.Task;
import main.infrastructure.CsvFileHandler;
import main.infrastructure.JsonFileHandler;
import main.kernel.FileHandler;
import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws Exception {
        FileHandler fileHandler = new CsvFileHandler(CSVFormat.DEFAULT.withHeader("id", "dateTime", "dueDate", "closeDate", "description", "state", "subTasks"));
        UpdateTaskHandler updateTaskHandler = new UpdateTaskHandler(fileHandler);
        DeleteTaskHandler deleteTaskHandler = new DeleteTaskHandler(fileHandler);

        UUID id = UUID.fromString("5856d2da-0389-4c65-bb46-9be5f0288af7");
        DeleteTask deleteTask = new DeleteTask(id);

        deleteTaskHandler.handle(deleteTask);
    }
}
