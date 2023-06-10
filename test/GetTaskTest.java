import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import main.application.command.update.UpdateTask;
import main.application.command.update.UpdateTaskHandler;
import main.domain.model.State;
import main.infrastructure.CsvFileHandler;
import main.infrastructure.JsonFileHandler;
import main.kernel.FileHandler;
import main.kernel.exception.EmptyFileException;
import main.kernel.exception.TaskNotFoundException;
import org.apache.commons.csv.CSVFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class GetTaskTest {
    private final FileHandler fileHandler = new JsonFileHandler();
    private final UpdateTaskHandler updateTaskHandler = new UpdateTaskHandler(fileHandler);

    @Test
    public void should_get_task_by_id() throws TaskNotFoundException, EmptyFileException {
        UUID id = UUID.fromString("f4883631-feea-4b60-99c9-29e4b3c917bc");

        boolean taskExists = fileHandler.getTask(id).isPresent();

        Assertions.assertTrue(taskExists);
    }

    @Test
    public void should_thrown_error_because_file_is_empty() {
        UUID id = UUID.fromString("f4883631-feea-4b60-99c9-29e4b3c917bc");

        Assertions.assertThrows(TaskNotFoundException.class, () -> {
            fileHandler.getTask(id);
        });
    }
}

