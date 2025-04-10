package md.com.jaru.andrusca.solidrancing.model;

import java.time.LocalTime;

public record LapData(String racerName, int number, LocalTime time) {
    @Override
    public String racerName() {
        return racerName;
    }

    @Override
    public int number() {
        return number;
    }

    @Override
    public LocalTime time() {
        return time;
    }
}
