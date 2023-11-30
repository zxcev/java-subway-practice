package subway.view;

import java.util.List;
import java.util.stream.Collectors;
import subway.domain.dto.FindPathResult;
import subway.domain.dto.StationDto;

public final class OutputView {

    private static final String LB = "\n";

    public void printPathResult(final FindPathResult result) {
        System.out.println("## 조회 결과");
        System.out.println(formatDistanceAndDuration(result));
        System.out.println(formatStationDtos(result.getStations()));
        System.out.println();
    }

    private String formatDistanceAndDuration(final FindPathResult result) {
        return String.join(LB, List.of(
                "---",
                unshiftPrefix(String.format("총 거리: %dkm", result.getTotalDistance())),
                unshiftPrefix(String.format("총 소요 시간: %d분", result.getTotalSpentDuration())),
                "---"
        ));

    }

    private String formatStationDtos(final List<StationDto> stationDtos) {
        return stationDtos.stream()
                .map(StationDto::getStationName)
                .map(this::unshiftPrefix)
                .collect(Collectors.joining(LB));
    }

    private String unshiftPrefix(final String text) {
        return String.format("[INFO] %s", text);
    }
}
