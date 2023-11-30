package subway.controller;

import subway.domain.PathFinder;
import subway.domain.dto.FindPathDto;
import subway.domain.dto.FindPathResult;
import subway.domain.feature.AppFeature;
import subway.domain.feature.QueryFeature;
import subway.handler.RetryHandler;
import subway.view.InputView;
import subway.view.OutputView;

public final class SubwayController {


    private final InputView inputView;

    private final OutputView outputView;

    private final PathFinder pathFinder;


    public SubwayController(
            final InputView inputView,
            final OutputView outputView,
            final PathFinder pathFinder
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pathFinder = pathFinder;
    }

    public void run() {
        final AppFeature appFeature = inputView.inputAppFeature();

        if (appFeature == AppFeature.QUIT) {
            return;
        }
        RetryHandler.retry(this::_selectQueryFeature);
        run();
    }

    private void _selectQueryFeature() {
        final QueryFeature queryFeature = inputView.inputQueryFeature();

        if (queryFeature == QueryFeature.BACKWARD) {
            return;
        }

        final FindPathDto findPathDto = inputView.inputFindPathDto();

        FindPathResult result = null;

        if (queryFeature == QueryFeature.MIN_DISTANCE) {
            result = pathFinder.findPathByShortestDistance(
                    findPathDto);
        }
        if (queryFeature == QueryFeature.MIN_DURATION) {
            result = pathFinder.findPathByMinDuration(findPathDto);
        }
        outputView.printPathResult(result);

    }

}
