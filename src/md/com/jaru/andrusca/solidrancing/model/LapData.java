package md.com.jaru.andrusca.solidrancing.model;

import java.time.LocalTime;
import java.util.Objects;

public final class LapData {
    private final String racerName;
    private final int number;
    private final LocalTime time;

    public LapData(String racerName, int number, LocalTime time) {
        this.racerName = racerName;
        this.number = number;
        this.time = time;
    }

    public String getRacerName() {
        return racerName;
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
        var that = (LapData) obj;
        return Objects.equals(this.racerName, that.racerName) &&
                this.number == that.number &&
                Objects.equals(this.time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racerName, number, time);
    }

    @Override
    public String toString() {
        return "LapData[" +
                "racerName=" + racerName + ", " +
                "number=" + number + ", " +
                "time=" + time + ']';
    }


}
