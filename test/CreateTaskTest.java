import main.application.command.create.CreateTask;
import main.application.command.create.CreateTaskHandler;
import main.application.command.update.UpdateTask;
import main.application.command.update.UpdateTaskHandler;
import main.domain.model.State;
import main.infrastructure.JsonFileHandler;
import main.kernel.FileHandler;
import main.kernel.exception.EmptyFileException;
import main.kernel.exception.SaveToFileException;
import main.kernel.exception.TaskNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class CreateTaskTest {
    private final FileHandler fileHandler = new JsonFileHandler("test/consoleagendaTest/dataTest.json");
    CreateTaskHandler createTaskHandler = new CreateTaskHandler(fileHandler);


    @Test
    public void should_create_a_new_task() throws SaveToFileException, TaskNotFoundException, EmptyFileException {
        CreateTask createTask = new CreateTask(new Date(), new Date(), "une description", State.TODO);

        createTaskHandler.handle(createTask);

        boolean taskExists = fileHandler.getTask(createTask.getId()).isPresent();

        Assertions.assertTrue(taskExists);
    }
}

