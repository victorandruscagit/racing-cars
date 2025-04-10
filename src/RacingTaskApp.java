import md.com.jaru.andrusca.solidrancing.argument.WorkingDirArgumentParser;
import md.com.jaru.andrusca.solidrancing.argument.WrongArgumentException;
import md.com.jaru.andrusca.solidrancing.file.FileProcessor;
import md.com.jaru.andrusca.solidrancing.file.FileProcessorImpl;

import java.nio.file.Path;

public class RacingTaskApp {
    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessorImpl();

        WorkingDirArgumentParser argumentParser = new WorkingDirArgumentParser(fileProcessor);
        Path workingDirPath = null;
        try {
            workingDirPath = argumentParser.parse(args);
        } catch (WrongArgumentException e) {
            System.out.println("Working directory specified wrong.");
            System.out.println("Error detail: " + e.getMessage());
            System.exit(1);
        }


    }
}
