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
    private final FileHandler fileHandler = new JsonFileHandler("test/consoleagendaTest/dataTest.json");
    private final FileHandler fileHandlerEmpty = new JsonFileHandler("test/consoleagendaTest/dataJsonEmptyTest.json");
    private final UpdateTaskHandler updateTaskHandler = new UpdateTaskHandler(fileHandler);

    @Test
    public void should_get_task_by_id() throws TaskNotFoundException, EmptyFileException {
        UUID id = UUID.fromString("32dba7cd-0dc6-4000-bd84-3daf85074631");

        boolean taskExists = fileHandler.getTask(id).isPresent();

        Assertions.assertTrue(taskExists);
    }

    @Test
    public void should_thrown_error_because_file_is_empty() {
        UUID id = UUID.fromString("2782d7fe-712f-4b31-b9f9-9bd1106c");

        Assertions.assertThrows(TaskNotFoundException.class, () -> {
            fileHandlerEmpty.getTask(id);
        });
    }
}

