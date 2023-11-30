package subway.domain.dto;

public final class StationDto {
    private final String stationName;

    public StationDto(final String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }
}
