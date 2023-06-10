import main.application.command.delete.DeleteTask;
import main.application.command.delete.DeleteTaskHandler;
import main.application.command.update.UpdateTask;
import main.application.command.update.UpdateTaskHandler;
import main.domain.model.State;
import main.infrastructure.JsonFileHandler;
import main.kernel.FileHandler;
import main.kernel.exception.TaskNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class DeleteTaskTest {
    private final FileHandler fileHandler = new JsonFileHandler("test/consoleagendaTest/dataTest.json");
    private final DeleteTaskHandler deleteTaskHandler = new DeleteTaskHandler(fileHandler);

    @Test
    public void should_failed_to_find_task_to_deleted() {
        UUID id = UUID.fromString("dbba1c03-f14c-48ed-aece-83f817aa69");
        DeleteTask deleteTask = new DeleteTask(id);

        Assertions.assertThrows(TaskNotFoundException.class, () -> {
            deleteTaskHandler.handle(deleteTask);
        });
    }
}

