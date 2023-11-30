package subway.domain;

import java.util.Objects;

public final class Section {

    private final Station srcStation;
    private final Station destStation;

    private final int distance;
    private final int duration;


    public Section(
            final Station srcStation,
            final Station destStation,
            final int distance,
            final int duration
    ) {
        this.srcStation = srcStation;
        this.destStation = destStation;
        this.distance = distance;
        this.duration = duration;
    }

    public Station getSrcStation() {
        return srcStation;
    }

    public Station getDestStation() {
        return destStation;
    }

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Section section = (Section) o;
        return distance == section.distance && duration == section.duration && Objects.equals(srcStation,
                section.srcStation) && Objects.equals(destStation, section.destStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(srcStation, destStation, distance, duration);
    }
}

