import main.application.command.update.UpdateTask;
import main.application.command.update.UpdateTaskHandler;
import main.domain.model.State;
import main.infrastructure.JsonFileHandler;
import main.kernel.FileHandler;

import java.util.Date;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws Exception {
        FileHandler fileHandler = new JsonFileHandler();
        UpdateTaskHandler updateTaskHandler = new UpdateTaskHandler(fileHandler);

        UUID id = UUID.fromString("2782d7fe-712f-4b31-b9f9-9bd1106c9126");
        UpdateTask updateTask = new UpdateTask(id, new Date(), new Date(), "updated", State.TODO);

        updateTaskHandler.handle(updateTask);
    }
}
