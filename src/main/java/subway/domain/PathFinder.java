package subway.domain;

import java.util.List;
import java.util.stream.Collectors;
import subway.domain.dto.FindPathDto;
import subway.domain.dto.FindPathResult;
import subway.domain.dto.StationDto;
import subway.domain.graph.StationsGraphByDistance;
import subway.domain.graph.StationsGraphByDuration;

public final class PathFinder {

    private final StationsGraphByDistance stationsGraphByDistance;
    private final StationsGraphByDuration stationsGraphByDuration;
    private final SectionRepository sectionRepository;

    public PathFinder(
            final StationsGraphByDistance stationsGraphByDistance,
            final StationsGraphByDuration stationsGraphByDuration,
            final SectionRepository sectionRepository
    ) {
        this.stationsGraphByDistance = stationsGraphByDistance;
        this.stationsGraphByDuration = stationsGraphByDuration;
        this.sectionRepository = sectionRepository;
    }

    // 경로 기준 + 출발역 + 도착역
    public FindPathResult findPathByShortestDistance(final FindPathDto findPathDto) {
        final Station srcStation = StationRepository.findByName(findPathDto.getSrc());
        final Station destStation = StationRepository.findByName(findPathDto.getDest());

        final List<Station> stations = stationsGraphByDistance.findShortestPath(srcStation, destStation);
        return toFindPathResult(stations);
    }


    public FindPathResult findPathByMinDuration(final FindPathDto findPathDto) {
        final Station srcStation = StationRepository.findByName(findPathDto.getSrc());
        final Station destStation = StationRepository.findByName(findPathDto.getDest());

        final List<Station> stations = stationsGraphByDuration.findShortestPath(srcStation, destStation);
        return toFindPathResult(stations);
    }

    private FindPathResult toFindPathResult(final List<Station> stations) {
        final List<Section> sections = sectionRepository.findSectionsByPath(stations);

        return new FindPathResult(
                calculateTotalDistance(sections),
                calculateTotalSpentDuration(sections),
                toStationDtos(stations)
        );
    }

    private int calculateTotalSpentDuration(final List<Section> sections) {
        return sections.stream()
                .map(Section::getDuration)
                .reduce(0, Integer::sum);
    }

    private int calculateTotalDistance(final List<Section> sections) {
        return sections.stream()
                .map(Section::getDistance)
                .reduce(0, Integer::sum);
    }

    private List<StationDto> toStationDtos(final List<Station> stations) {
        return stations.stream()
                .map(s -> new StationDto(s.getName()))
                .collect(Collectors.toUnmodifiableList());
    }
}
