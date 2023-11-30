package subway.domain.graph;

import java.util.List;
import subway.domain.Section;
import subway.domain.Station;

public interface StationsGraph {
    void addStations(List<Station> stations);

    void addSections(List<Section> sections);

    List<Station> findShortestPath(final Station srcStation, final Station destStation);
}
