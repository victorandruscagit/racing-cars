package md.com.jaru.andrusca.racingtask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("-workingdir")) {
            System.err.println("You must specify parameter '-workingdir' with path");
            System.exit(1);
        }

        String[] keyAndValue = args[0].split("=");
        if (keyAndValue.length != 2) {
            System.err.println("You must specify parameter '-workingdir'" +
                    " with correct format : 'workingdir={value}");
            System.exit(1);
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
                System.exit(4);
            }
        } catch (IOException e) {
            System.err.println("Something goes wrong with dir .Reason: " + e.getMessage());
            System.exit(5);

        }

        try {
            for (Path path : Files.newDirectoryStream(dirPath)) {
                processFile(path);
            }
        } catch (IOException e) {
            System.err.println("Something goes wrong with file processing");
            System.exit(6);

        }


    }

    private static void processFile(Path filePath) {
        if (!filePath.toString().endsWith(".result")) {
            System.out.println("File is skipped: " + filePath);

        }

        List<RoundData> roundData = new ArrayList<>();
        try {
            System.out.println(filePath);

            for (String s : Files.readAllLines(filePath)) {
                String[] lineParts = s.split(";");

                String racerName = lineParts[0];
                int roundNumber = Integer.parseInt(lineParts[1]);
                LocalTime time = LocalTime.parse(lineParts[2]);

                roundData.add(new RoundData(racerName, roundNumber, time));
            }
        } catch (IOException e) {
            System.err.println("Error with reading file" + filePath + ". Reason: " + e.getMessage());


        }
        Comparator<RoundData> roundDataComparator = (o1, o2) -> {
            if (o1.getTime().equals(o2.getTime())) {
                return Integer.compare(o1.getNumber(), o2.getNumber());
            } else {
                return o1.getTime().compareTo(o2.getTime());
            }
        };

        List<RoundData> bestRounds = roundData.stream()
                .collect(Collectors.groupingBy(RoundData::getRacerName, Collectors.maxBy(roundDataComparator)))
                .values()
                .stream()
                .map(Optional::get)
                .sorted(roundDataComparator)
                .toList();

        int maxNameLenght = bestRounds.stream()
                .map(roundData1 -> roundData1.getRacerName().length())
                .max(Integer::compareTo)
                .get();

        String pattern = "|%3s|%" + maxNameLenght + "s|%4s|%12s|\n";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

        StringBuilder result = new StringBuilder();

        addHeaders(result, maxNameLenght);
        for (RoundData roundStringEntry : bestRounds) {
            result.append(pattern.formatted(
                    roundStringEntry.getRacerName(),
                    roundStringEntry.getNumber(),
                    roundStringEntry.getTime().format(formatter)
            ));
        }

        addHeaders(result, maxNameLenght);
        try {
            String baseDir = filePath.getParent().toString();
            String newFileName = getFileBaseName(filePath.getFileName().toString() + "" + ".calculated");
            Path outputPath = Path.of(baseDir, newFileName);
            Files.writeString(outputPath, result.toString(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    private static String getFileBaseName(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return fileName;
        } else {
            return fileName.substring(0, index);
        }
    }

    private static void addHeaders(StringBuilder result, int maxNameLength) {
        result.append("|---|");
        result.append("-".repeat(maxNameLength));
        result.append("|");
        result.append("--|-----------|\n");
    }

    static final class RoundData {
        private final String recerName;
        private final int number;
        private final LocalTime time;

        RoundData(String recerName, int number, LocalTime time) {
            this.recerName = recerName;
            this.number = number;
            this.time = time;
        }

        public String getRacerName() {
            return recerName;
        }

        public int getNumber() {
            return number;
        }

        public LocalTime getTime() {
            return time;
        }


        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (RoundData) obj;
            return Objects.equals(this.recerName, that.recerName) &&
                    this.number == that.number &&
                    Objects.equals(this.time, that.time);
        }

        @Override
        public int hashCode() {
            return Objects.hash(recerName, number, time);
        }

        @Override
        public String toString() {
            return "RoundData[" +
                    "recerName=" + recerName + ", " +
                    "number=" + number + ", " +
                    "time=" + time + ']';
        }

    }


}



