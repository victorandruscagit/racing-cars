package md.com.jaru.andrusca.solidrancing.argument;

import md.com.jaru.andrusca.solidrancing.file.FileProcessor;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class WorkingDirArgumentParser {
    public static final String ARGUMENT_SEPARATOR = "=";
    public static final String WORKING_DIR_ARGUMENT = "-workingdir";
    private FileProcessor fileProcessor;

    public WorkingDirArgumentParser(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    public Path parse(String[] args) {
        String directoryName = extractArgumentValue(args);
        return validateAndParsePath(directoryName);

    }

    private String extractArgumentValue(String[] args) {
        String argumentValue = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith(WORKING_DIR_ARGUMENT + ARGUMENT_SEPARATOR)) {
                argumentValue = args[i];
            }
        }
        if (argumentValue == null) {
            throw new WrongArgumentException("Argument : " + WORKING_DIR_ARGUMENT + " is not found");
        }
        String[] splitArgumetValue = argumentValue.split(ARGUMENT_SEPARATOR);
        if (splitArgumetValue.length != 2) {
            String message = "Argument has wrong value.Should look like  this : " + WORKING_DIR_ARGUMENT + "=value";
            throw new WrongArgumentException(message);
        }
        return splitArgumetValue[1];
    }

    private Path validateAndParsePath(String directoryName) {
        Path dirPath;
        try {
            dirPath = Path.of(directoryName);
        } catch (InvalidPathException e) {
            throw new WrongArgumentException("Wrong value of directory path, " + e);
        }
        if (!Files.isDirectory(dirPath)) {
            throw new WrongArgumentException("Path is related with no correct  directory");
        }
        if (!fileProcessor.openDirectoryStream(dirPath).iterator().hasNext()) {
            throw new WrongArgumentException("Path is related with no correct  directory: it is emmtpy");
        }


        return dirPath;
    }


}
