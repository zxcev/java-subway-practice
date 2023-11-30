package subway.domain;

import java.util.List;
import subway.domain.graph.StationsGraph;

public final class Initializer {

    private static void initLines() {
        LineRepository.addLine(new Line("2호선"));
        LineRepository.addLine(new Line("3호선"));
        LineRepository.addLine(new Line("신분당선"));
    }

    private static void initStations() {
        StationRepository.addStation(new Station("교대역"));
        StationRepository.addStation(new Station("강남역"));
        StationRepository.addStation(new Station("역삼역"));
        StationRepository.addStation(new Station("남부터미널역"));
        StationRepository.addStation(new Station("양재역"));
        StationRepository.addStation(new Station("매봉역"));
        StationRepository.addStation(new Station("양재시민의숲역"));
    }

    private static void initSections() {
        final Station 교대역 = StationRepository.findByName("교대역");
        final Station 강남역 = StationRepository.findByName("강남역");
        final Station 역삼역 = StationRepository.findByName("역삼역");
        final Station 남부터미널역 = StationRepository.findByName("남부터미널역");
        final Station 양재역 = StationRepository.findByName("양재역");
        final Station 매봉역 = StationRepository.findByName("매봉역");
        final Station 양재시민의숲역 = StationRepository.findByName("양재시민의숲역");

        // line 2
        SectionRepository.addSection(new Section(교대역, 강남역, 2, 3));
        SectionRepository.addSection(new Section(강남역, 역삼역, 2, 3));

        // line 3
        SectionRepository.addSection(new Section(교대역, 남부터미널역, 3, 2));
        SectionRepository.addSection(new Section(남부터미널역, 양재역, 6, 5));
        SectionRepository.addSection(new Section(양재역, 매봉역, 1, 1));

        // line bd
        SectionRepository.addSection(new Section(강남역, 양재역, 2, 8));
        SectionRepository.addSection(new Section(양재역, 양재시민의숲역, 10, 3));
    }

    // graph
    public static void initGraphs(final StationsGraph graph) {
        final List<Station> stations = StationRepository.stations();
        final List<Section> sections = SectionRepository.sections();
        graph.addStations(stations);
        graph.addSections(sections);
    }

    public static void init() {
        initLines();
        initStations();
        initSections();
    }
}
