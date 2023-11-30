package subway.domain.feature;

public enum QueryFeature {
    MIN_DISTANCE("1"),
    MIN_DURATION("2"),
    BACKWARD("B");

    private static final String INVALID_QUERY_FEATURE_MSG = "유효하지 않은 기능입니다.\n반드시 1, 2, B 중 하나를 입력해주세요.";
    private final String value;

    QueryFeature(final String value) {
        this.value = value;
    }

    public static QueryFeature from(final String input) {
        if ("1".equals(input)) {
            return MIN_DISTANCE;
        }
        if ("2".equals(input)) {
            return MIN_DURATION;
        }
        if ("B".equals(input)) {
            return BACKWARD;
        }

        throw new IllegalArgumentException(INVALID_QUERY_FEATURE_MSG);
    }
}
