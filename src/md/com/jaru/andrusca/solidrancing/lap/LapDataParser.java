package md.com.jaru.andrusca.solidrancing.lap;

import md.com.jaru.andrusca.solidrancing.model.LapData;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LapDataParser {

    private static final String VALUE_SEPARATOR = ";";

    public List<LapData> parseToLapData(List<String> lines) {
        List<LapData> lapDataList = new ArrayList<>();

        try {
            for (String line : lines) {
                lapDataList.add(parseLapDataLines(line));
            }
        } catch (RuntimeException e) {
            throw new WrongLapDataException("Line is wrong .Message :" + e.getMessage(), e);
        }

        return lapDataList;

    }

    private LapData parseLapDataLines(String line) {
        String[] lineParts = line.split(VALUE_SEPARATOR);

        String racerName = lineParts[0].trim();
        int roundNumber = Integer.parseInt(lineParts[1].trim());
        LocalTime time = LocalTime.parse(lineParts[2].trim());

        return new LapData(racerName, roundNumber, time);
    }
}
