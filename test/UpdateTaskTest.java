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

public class UpdateTaskTest {
    private final FileHandler fileHandler = new JsonFileHandler();
    private final UpdateTaskHandler updateTaskHandler = new UpdateTaskHandler(fileHandler);

    @Test
    public void should_failed_to_find_task_to_updated() {
        UUID id = UUID.fromString("2782d7fe-712f-4b31-b9f9-9bd1106c926");
        UpdateTask updateTask = new UpdateTask(id, new Date(), new Date(), "updated", State.TODO);

        Assertions.assertThrows(TaskNotFoundException.class, () -> {
            updateTaskHandler.handle(updateTask);
        });
    }
}

