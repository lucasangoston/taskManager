import main.infrastructure.CommandLineApp;
import main.infrastructure.CsvFileHandler;
import main.kernel.FileHandler;
import org.apache.commons.csv.CSVFormat;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        FileHandler fileHandler = new CsvFileHandler(CSVFormat.DEFAULT,"src/main/consoleagenda/data.csv");
        CommandLineApp commandLineApp = new CommandLineApp(fileHandler);
        commandLineApp.usage();

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String commandLine = scanner.nextLine();
        scanner.close();

        commandLineApp.run(commandLine);

    }
}
