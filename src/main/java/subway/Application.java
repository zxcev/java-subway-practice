package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.domain.Initializer;
import subway.domain.PathFinder;
import subway.domain.SectionRepository;
import subway.domain.graph.StationsGraphByDistance;
import subway.domain.graph.StationsGraphByDuration;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Initializer.init();
        final StationsGraphByDistance stationsGraphByDistance = new StationsGraphByDistance();
        final StationsGraphByDuration stationsGraphByDuration = new StationsGraphByDuration();
        final SectionRepository sectionRepository = new SectionRepository();
        Initializer.initGraphs(stationsGraphByDistance);
        Initializer.initGraphs(stationsGraphByDuration);

        final PathFinder pathFinder = new PathFinder(
                stationsGraphByDistance,
                stationsGraphByDuration,
                sectionRepository
        );
        final Scanner scanner = new Scanner(System.in);
        final InputView inputView = new InputView(scanner);
        final OutputView outputView = new OutputView();

        final SubwayController subwayController = new SubwayController(
                inputView,
                outputView,
                pathFinder
        );

        subwayController.run();
    }

}
