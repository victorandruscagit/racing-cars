package md.com.jaru.andrusca.racingtask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("-workingdir")) {
            System.err.println("You must specify parameter '-workingdir' with path");
            System.exit(1);

            String  [] keyAndValue = args[0].split("=");
            if (keyAndValue.length != 2) {
                System.err.println("You must specify parameter '-workingdir'" +
                        " with correct format : 'workingdir={value}");
                System.exit(1);

            }

        }
        String dirValue = args[1];
        Path dirPath = null;
        try {
            dirPath = Path.of(dirValue);
        } catch (InvalidPathException e) {
            System.err.println("Path is invalid :" + dirValue);
            System.err.println("Eror details :" + e.getMessage());
            System.exit(2);

        }
        if (!Files.isDirectory(dirPath)) {
            System.err.println("Directory is incorrect");
            System.exit(3);
        }
        try {
            if (!Files.newDirectoryStream(dirPath).iterator().hasNext()) {
                System.out.println("Directory is empty ");
            }
        } catch (IOException e) {

        }


    }

    record RoundData(String recerName, int number, LocalTime time) {
        public String getRacerName() {
            return recerName;
        }

        public int getNumber() {
            return number;
        }

        public LocalTime getTime() {
            return time;
        }
    }


}



