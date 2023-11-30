package subway.domain.graph;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.Section;
import subway.domain.Station;

final class StationsGraphTest {

    @DisplayName("최단 거리 Station 테스트")
    @Test
    void getShortestDistancePath() {
        // given
        final StationsGraphByDistance graph = new StationsGraphByDistance();

        final Station stationA = new Station("a");
        final Station stationB = new Station("b");
        final Station stationC = new Station("c");
        final Section sectionAB = new Section(stationA, stationB, 3, 5);
        final Section sectionAC = new Section(stationA, stationC, 10, 3);
        final Section sectionBC = new Section(stationB, stationC, 5, 1);

        // when
        graph.addStations(List.of(stationA, stationB, stationC));
        graph.addSections(List.of(sectionAB, sectionAC, sectionBC));

        // then
        final List<Station> shortestPath = graph.findShortestPath(stationA, stationB);
        assertThat(shortestPath.size()).isEqualTo(2);
        assertThat(shortestPath.get(0)).isEqualTo(stationA);
        assertThat(shortestPath.get(1)).isEqualTo(stationB);

        final List<Station> shortestPath2 = graph.findShortestPath(stationA, stationC);
        assertThat(shortestPath2.size()).isEqualTo(3);
        assertThat(shortestPath2.get(0)).isEqualTo(stationA);
        assertThat(shortestPath2.get(1)).isEqualTo(stationB);
        assertThat(shortestPath2.get(2)).isEqualTo(stationC);

        final List<Station> shortestPath3 = graph.findShortestPath(stationB, stationC);
        assertThat(shortestPath3.size()).isEqualTo(2);
        assertThat(shortestPath3.get(0)).isEqualTo(stationB);
        assertThat(shortestPath3.get(1)).isEqualTo(stationC);

        final List<Station> shortestPath4 = graph.findShortestPath(stationC, stationA);
        assertThat(shortestPath4.size()).isEqualTo(3);
        assertThat(shortestPath4.get(0)).isEqualTo(stationC);
        assertThat(shortestPath4.get(1)).isEqualTo(stationB);
        assertThat(shortestPath4.get(2)).isEqualTo(stationA);
    }

    @DisplayName("최단 시간 Station 테스트")
    @Test
    void getMinDurationPath() {
        // given
        final StationsGraphByDuration graph = new StationsGraphByDuration();

        final Station stationA = new Station("a");
        final Station stationB = new Station("b");
        final Station stationC = new Station("c");
        final Section sectionAB = new Section(stationA, stationB, 3, 5);
        final Section sectionAC = new Section(stationA, stationC, 5, 3);
        final Section sectionBC = new Section(stationB, stationC, 10, 1);

        // when
        graph.addStations(List.of(stationA, stationB, stationC));
        graph.addSections(List.of(sectionAB, sectionAC, sectionBC));

        // then
        final List<Station> shortestPath = graph.findShortestPath(stationA, stationB);
        assertThat(shortestPath.size()).isEqualTo(3);
        assertThat(shortestPath.get(0)).isEqualTo(stationA);
        assertThat(shortestPath.get(1)).isEqualTo(stationC);
        assertThat(shortestPath.get(2)).isEqualTo(stationB);

        final List<Station> shortestPath2 = graph.findShortestPath(stationA, stationC);
        assertThat(shortestPath2.size()).isEqualTo(2);
        assertThat(shortestPath2.get(0)).isEqualTo(stationA);
        assertThat(shortestPath2.get(1)).isEqualTo(stationC);

        final List<Station> shortestPath3 = graph.findShortestPath(stationB, stationC);
        assertThat(shortestPath3.size()).isEqualTo(2);
        assertThat(shortestPath3.get(0)).isEqualTo(stationB);
        assertThat(shortestPath3.get(1)).isEqualTo(stationC);

        final List<Station> shortestPath4 = graph.findShortestPath(stationC, stationA);
        assertThat(shortestPath4.size()).isEqualTo(2);
        assertThat(shortestPath4.get(0)).isEqualTo(stationC);
        assertThat(shortestPath4.get(1)).isEqualTo(stationA);

    }


}