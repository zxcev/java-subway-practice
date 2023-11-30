package subway.view;

import java.util.Scanner;
import subway.domain.dto.FindPathDto;
import subway.domain.feature.AppFeature;
import subway.domain.feature.QueryFeature;
import subway.handler.RetryHandler;

public final class InputView {

    private static final String SELECT_FEATURE_MSG = "## 원하는 기능을 선택하세요.";


    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public AppFeature inputAppFeature() {
        return RetryHandler.retry(this::_inputAppFeature);
    }

    public QueryFeature inputQueryFeature() {
        return RetryHandler.retry(this::_inputQueryFeature);
    }

    public FindPathDto inputFindPathDto() {
        final String srcStationName = _inputSrcStationName();
        final String destStationName = _inputDestStationName();

        if (srcStationName.equals(destStationName)) {
            throw new IllegalArgumentException("출발역과 도착역이 동일합니다.");
        }

        return new FindPathDto(
                srcStationName,
                destStationName
        );
    }

    private AppFeature _inputAppFeature() {
        System.out.println("## 메인 화면\n"
                + "1. 경로 조회\n"
                + "Q. 종료\n");
        System.out.println(SELECT_FEATURE_MSG);
        final String input = readLine();
        System.out.println();
        return AppFeature.from(input);
    }

    private QueryFeature _inputQueryFeature() {
        System.out.println("## 경로 기준\n"
                + "1. 최단 거리\n"
                + "2. 최소 시간\n"
                + "B. 돌아가기\n");
        System.out.println(SELECT_FEATURE_MSG);
        final String input = readLine();
        System.out.println();
        return QueryFeature.from(input);
    }

    private String _inputSrcStationName() {
        System.out.println("## 출발역을 입력하세요.");
        final String input = readLine();
        System.out.println();
        return input;
    }

    private String _inputDestStationName() {
        System.out.println("## 도착역을 입력하세요.");
        final String input = readLine();
        System.out.println();
        return input;
    }

    private String readLine() {
        return scanner.nextLine().trim();
    }
}
