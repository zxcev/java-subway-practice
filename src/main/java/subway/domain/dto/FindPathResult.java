package subway.domain.dto;

import java.util.List;

public final class FindPathResult {

    private final int totalDistance;
    private final int totalSpentDuration;
    private final List<StationDto> stations;

    public FindPathResult(
            final int totalDistance,
            final int totalSpentDuration,
            final List<StationDto> stations
    ) {
        this.totalDistance = totalDistance;
        this.totalSpentDuration = totalSpentDuration;
        this.stations = stations;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getTotalSpentDuration() {
        return totalSpentDuration;
    }

    public List<StationDto> getStations() {
        return stations;
    }
}
