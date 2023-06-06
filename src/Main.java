import main.infrastructure.JsonFileHandler;

public class Main {
    public static void main(String[] args) {
        JsonFileHandler jsonFileHandler = new JsonFileHandler();
        System.out.println(jsonFileHandler.getTasks());
    }
}
