package subway.domain.graph;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Section;
import subway.domain.Station;

public final class StationsGraphByDuration implements StationsGraph {

    private final WeightedMultigraph<Station, DefaultWeightedEdge> graph
            = new WeightedMultigraph<>(DefaultWeightedEdge.class);

    private DijkstraShortestPath<Station, DefaultWeightedEdge> dijkstraShortestPath;

    @Override
    public void addStations(List<Station> stations) {
        for (final Station station : stations) {
            graph.addVertex(station);
        }
    }

    @Override
    public void addSections(final List<Section> sections) {
        for (final Section section : sections) {
            final DefaultWeightedEdge edge = graph.addEdge(
                    section.getSrcStation(),
                    section.getDestStation()
            );
            graph.setEdgeWeight(edge, section.getDistance());
        }
        dijkstraShortestPath = new DijkstraShortestPath<>(graph);
    }

    @Override
    public List<Station> findShortestPath(
            final Station srcStation,
            final Station destStation
    ) {
        return dijkstraShortestPath
                .getPath(srcStation, destStation)
                .getVertexList();
    }

}
