package md.com.jaru.andrusca.solidrancing.lap;

import md.com.jaru.andrusca.solidrancing.model.LapData;

import java.util.*;

public class BestLapCalculator {
    private static Comparator<LapData> ASCENDING_LAP_COMPARATOR = (o1, o2) -> {
        if (o1.getTime().equals(o2.getTime())) {
            return Integer.compare(o1.getNumber(), o2.getNumber());
        } else {
            return o1.getTime().compareTo(o2.getTime());
        }
    };

    public List<LapData> calculate(List<LapData> simpleData) {
        Map<String, List<LapData>> groupByRacerName = new HashMap<>();

        for (LapData lapData : simpleData) {
            if (groupByRacerName.containsKey(lapData.getRacerName())) {
                List<LapData> lapData1 = groupByRacerName.get(lapData.getRacerName());
                lapData1.add(lapData);
            } else {
                List<LapData> laps = new ArrayList<>();
                laps.add(lapData);
                groupByRacerName.put(lapData.getRacerName(), laps);
            }
        }
        for (List<LapData> laps : groupByRacerName.values()) {
            Collections.sort(laps, new AscendingLapComparator());
        }

        List<LapData> bestLaps = new ArrayList<>();
        for (List<LapData> laps : groupByRacerName.values()) {
            bestLaps.add(laps.get(0));
        }

        Collections.sort(bestLaps, new AscendingLapComparator());
        return bestLaps;
    }


}


