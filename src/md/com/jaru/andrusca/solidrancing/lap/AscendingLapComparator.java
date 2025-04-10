package md.com.jaru.andrusca.solidrancing.lap;

import md.com.jaru.andrusca.solidrancing.model.LapData;

import java.util.Comparator;

public class AscendingLapComparator implements Comparator<LapData> {


    @Override
    public int compare(LapData o1, LapData o2) {
        if (o1.getTime().equals(o2.getTime())) {
            return Integer.compare(o1.getNumber(), o2.getNumber());
        } else {
            return o1.getTime().compareTo(o2.getTime());
        }
    }
}
